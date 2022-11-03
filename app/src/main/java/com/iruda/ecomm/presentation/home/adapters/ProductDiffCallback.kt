package com.iruda.ecomm.presentation.home.adapters

import androidx.recyclerview.widget.DiffUtil
import com.iruda.ecomm.domain.home.entities.Product

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}
