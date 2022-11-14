package com.iruda.ecomm.presentation.cart.adapters

import androidx.recyclerview.widget.DiffUtil
import com.iruda.ecomm.domain.cart.entities.CartProduct

class CartItemDiffCallback : DiffUtil.ItemCallback<CartProduct>() {

    override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
        return oldItem == newItem
    }
}