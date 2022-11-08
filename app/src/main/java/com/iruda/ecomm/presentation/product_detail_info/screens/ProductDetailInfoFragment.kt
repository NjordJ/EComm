package com.iruda.ecomm.presentation.product_detail_info.screens

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.iruda.ecomm.R
import com.iruda.ecomm.databinding.ProductDetailInfoBinding

class ProductDetailInfoFragment : Fragment(), MenuProvider {

    private val args: ProductDetailInfoFragmentArgs by navArgs()

    private var _binding: ProductDetailInfoBinding? = null
    private val binding: ProductDetailInfoBinding
        get() = _binding ?: throw  RuntimeException("ProductDetailInfoBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductDetailInfoBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        binding.apply {
            args.product.apply {
                loadProductImage(imageView = imageViewProductImage, imageUrl = image)
                textViewProductName.text = title
                textViewProductPrice.text = price.toString()
                ratingBarProductRating.rating = rating.rate.toFloat()
                textViewProductRatingValue.text = rating.rate.toString()
                textViewDescription.text = description

            }
        }
    }

    private fun loadProductImage(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView)
            .load(imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_error)
            .into(imageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
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