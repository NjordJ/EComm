package com.iruda.ecomm.presentation.home.screens

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.iruda.ecomm.R
import com.iruda.ecomm.data.product.models.ProductModel
import com.iruda.ecomm.databinding.FragmentHomeBinding
import com.iruda.ecomm.domain.product.entities.Product
import com.iruda.ecomm.presentation.home.adapters.ProductAdapter
import com.iruda.ecomm.presentation.home.viewmodels.HomeViewModel
import com.iruda.ecomm.util.onQueryTextChanged
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeFragment : Fragment(), MenuProvider {

    private lateinit var viewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw  RuntimeException("FragmentHomeBinding is null")

    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createCarousel()
        observeViewModel()

    }

    private fun observeViewModel() {
        val adapter = ProductAdapter(requireContext())

        binding.recyclerViewHomeProducts.adapter = adapter
        binding.recyclerViewHomeProducts.itemAnimator = null

        adapter.onProductClickListener = object : ProductAdapter.OnProductClickListener {
            override fun onProductClick(product: Product) {
                launchDetailScreen(product = product)
            }
        }

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.productList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun launchDetailScreen(product: Product) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToProductDetailInfoFragment(
                product
            )
        findNavController().navigate(action)
    }

    private fun createCarousel() {
        binding.homeCarousel.registerLifecycle(viewLifecycleOwner)
        val carouselItems = mutableListOf<CarouselItem>()

        carouselItems.add(
            CarouselItem(
                imageDrawable = R.drawable.ic_launcher_background
            )
        )
        carouselItems.add(
            CarouselItem(
                imageDrawable = R.drawable.ic_launcher_background
            )
        )
        carouselItems.add(
            CarouselItem(
                imageDrawable = R.drawable.ic_launcher_background
            )
        )
        binding.homeCarousel.setData(carouselItems)
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