package com.iruda.ecomm.presentation.category.adapters

import androidx.recyclerview.widget.DiffUtil
import com.iruda.ecomm.domain.category.entities.Category

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}