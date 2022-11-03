package com.iruda.ecomm.presentation.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.iruda.ecomm.R
import com.iruda.ecomm.databinding.HomeProductItemBinding
import com.iruda.ecomm.domain.home.entities.Product

class ProductAdapter(
    private val context: Context
) : ListAdapter<Product, ProductViewHolder>(ProductDiffCallback()) {

    var onProductClickListener: OnProductClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = HomeProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        with(holder.binding) {
            with(product) {
                textViewProductName.text = title
                textViewProductCategory.text = category
                textViewProductPrice.text = price.toString()
                textViewProductRatingValue.text = rating.rate.toString()
                Glide.with(holder.itemView)
                    .load(image)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageViewProductImage)
                root.setOnClickListener {
                    onProductClickListener?.onProductClick(this)
                }
            }
        }
    }

    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }
}