package com.iruda.ecomm.presentation.cart.screens

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.iruda.ecomm.R
import com.iruda.ecomm.databinding.FragmentCartBinding
import com.iruda.ecomm.domain.cart.entities.CartProduct
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.presentation.cart.adapters.CartItemAdapter
import com.iruda.ecomm.presentation.cart.viewmodels.CartViewModel
import com.iruda.ecomm.presentation.home.screens.HomeFragmentDirections
import com.iruda.ecomm.util.onQueryTextChanged
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment(), MenuProvider {

    private val viewModel by viewModel<CartViewModel>()

    private var _binding: FragmentCartBinding? = null
    private val binding: FragmentCartBinding
        get() = _binding ?: throw  RuntimeException("FragmentCartBinding is null")

    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        val adapter = CartItemAdapter(requireContext())

        binding.recyclerViewCart.adapter = adapter
        binding.recyclerViewCart.itemAnimator = null

        adapter.onCartItemClickListener = object : CartItemAdapter.OnCartItemClickListener {
            override fun onCartItemClick(cartProduct: CartProduct) {
                //launchDetailScreen(product = cartProduct.id)
            }
        }

        viewModel.cart.observe(viewLifecycleOwner) {
            binding.apply {
                textViewCartTotalPrice.text = it.total.toString()
                textViewTotalQuantity.text = it.totalQuantity.toString()
            }
            adapter.submitList(it.products)
        }
    }

    private fun launchDetailScreen(product: Product) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToProductDetailInfoFragment(
                product
            )
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.search_action_appbar)
        searchView = searchItem.actionView as SearchView

        val pendingQuery = viewModel.searchQuery.value
        if (pendingQuery != null && pendingQuery.isNotEmpty()) {
            searchItem.expandActionView()
            searchView.setQuery(pendingQuery, false)
        }

        searchView.onQueryTextChanged {
            viewModel.postSearch(query = it)
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.notification_action_appbar -> {
                true
            }
            else -> false
        }
    }

}