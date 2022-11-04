package com.iruda.ecomm.presentation.category.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.iruda.ecomm.databinding.CategoryItemBinding
import com.iruda.ecomm.domain.category.entities.Category

class CategoryAdapter(
    private val context: Context
) : ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallback()) {

    var onCategoryClickListener: CategoryAdapter.OnCategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        with(holder.binding) {
            with(category) {
                textViewCategoryName.text = name
                root.setOnClickListener {
                    onCategoryClickListener?.onCategoryClick(this)
                }
            }
        }
    }

    interface OnCategoryClickListener {
        fun onCategoryClick(category: Category)
    }
}