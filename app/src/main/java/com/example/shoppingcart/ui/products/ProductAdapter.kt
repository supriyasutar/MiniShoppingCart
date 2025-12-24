package com.example.shoppingcart.ui.products

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.data.model.Product
import com.example.shoppingcart.databinding.ItemProductBinding

class ProductAdapter(
    private val list: List<Product>,
    private val onAddClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    inner class VH(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val product = list[position]

        holder.binding.apply {
            //imgProduct.setImageResource(product.imageRes)
            tvName.text = product.name
            tvPrice.text = "₹${product.price}"
            tvTax.text = "Tax: ${product.taxPercent}%"

            if (product.originalPrice != null) {
                tvOriginalPrice.visibility = View.VISIBLE
                tvOriginalPrice.text = "₹${product.originalPrice}"
                tvOriginalPrice.paintFlags =
                    tvOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                tvOriginalPrice.visibility = View.GONE
            }

            btnAdd.setOnClickListener { onAddClick(product) }
        }
    }

    override fun getItemCount() = list.size
}
