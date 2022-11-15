package com.iruda.ecomm.presentation.cart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.iruda.ecomm.R
import com.iruda.ecomm.databinding.CartItemBinding
import com.iruda.ecomm.domain.cart.entities.CartProduct

class CartItemAdapter(
    private val context: Context
) : ListAdapter<CartProduct, CartItemViewHolder>(CartItemDiffCallback()) {

    var onCartItemClickListener: OnCartItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding = CartItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = getItem(position)
        with(holder.binding) {
            with(cartItem) {
                textViewCartProductName.text = title
                textViewCartProductPrice.text = price.toString()
                textViewCartProductAmount.text = quantity.toString()
                Glide.with(holder.itemView)
                    .load(R.drawable.ic_launcher_background)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageViewCartProductImage)
                root.setOnClickListener {
                    onCartItemClickListener?.onCartItemClick(this)
                }
            }
        }
    }

    interface OnCartItemClickListener {
        fun onCartItemClick(cartProduct: CartProduct)
    }
}