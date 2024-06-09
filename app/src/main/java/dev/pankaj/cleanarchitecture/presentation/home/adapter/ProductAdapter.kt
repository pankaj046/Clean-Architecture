package dev.pankaj.cleanarchitecture.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import dev.pankaj.cleanarchitecture.databinding.ItemProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var data: List<Product> = mutableListOf()

    inner class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = data[position]
        with(holder.binding) {
            titleTextView.text = product.title
            priceTextView.text = product.price?.let { "$$it" }
            descriptionTextView.text = product.description
            categoryTextView.text = product.category
            ratingTextView.text = product.rating?.let { "Rating: ${it.rate} (${it.count} reviews)" }

            Glide.with(imageView.context)
                .load(product.image)
                .into(imageView)
        }
    }

    override fun getItemCount(): Int = data.size

    fun updateProduct(newData: List<Product>) {
        val diffCallback = ProductDiffCallback(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data = newData
        diffResult.dispatchUpdatesTo(this)
    }
}

