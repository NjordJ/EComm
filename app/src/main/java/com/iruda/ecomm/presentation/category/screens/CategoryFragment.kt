package com.iruda.ecomm.presentation.category.screens

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.iruda.ecomm.R
import com.iruda.ecomm.databinding.FragmentCategoryBinding
import com.iruda.ecomm.presentation.category.adapters.CategoryAdapter
import com.iruda.ecomm.presentation.home.screens.viewmodels.CategoryViewModel

class CategoryFragment : Fragment(), MenuProvider {

    private lateinit var viewModel: CategoryViewModel

    private var _binding: FragmentCategoryBinding? = null
    private val binding: FragmentCategoryBinding
        get() = _binding ?: throw  RuntimeException("FragmentCategoryBinding is null")

    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        val adapter = CategoryAdapter(requireContext())

        binding.recyclerViewCategories.adapter = adapter
        binding.recyclerViewCategories.itemAnimator = null

        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        viewModel.categoryList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.search_action_appbar)
        searchView = searchItem.actionView as SearchView
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