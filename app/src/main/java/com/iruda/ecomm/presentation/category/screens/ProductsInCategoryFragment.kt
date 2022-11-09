package com.iruda.ecomm.presentation.category.screens

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iruda.ecomm.R
import com.iruda.ecomm.databinding.FragmentProductsInCategoryBinding
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.presentation.category.viewmodels.ProductsInCategoryViewModel
import com.iruda.ecomm.presentation.home.adapters.ProductAdapter
import com.iruda.ecomm.util.onQueryTextChanged
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsInCategoryFragment : Fragment(), MenuProvider {

    private val viewModel by viewModel<ProductsInCategoryViewModel>()

    private val args: ProductsInCategoryFragmentArgs by navArgs()

    private var _binding: FragmentProductsInCategoryBinding? = null
    private val binding: FragmentProductsInCategoryBinding
        get() = _binding ?: throw  RuntimeException("FragmentProductsInCategoryBinding is null")

    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsInCategoryBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        val adapter = ProductAdapter(requireContext())

        binding.recyclerViewProductsInCategory.adapter = adapter
        binding.recyclerViewProductsInCategory.itemAnimator = null

        adapter.onProductClickListener = object : ProductAdapter.OnProductClickListener {
            override fun onProductClick(product: Product) {
                launchDetailScreen(product = product)
            }
        }

        //TODO: List in log showing more than one time somehow

        viewModel.getProductsInCategory(args.categoryName).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun launchDetailScreen(product: Product) {
        val action =
            ProductsInCategoryFragmentDirections.actionProductsInCategoryFragmentToProductDetailInfoFragment(
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
            R.id.search_action_appbar -> {
                true
            }
            R.id.notification_action_appbar -> {
                true
            }
            else -> false
        }
    }

}