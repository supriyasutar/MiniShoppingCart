package com.example.shoppingcart.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.data.model.CartItem
import com.example.shoppingcart.databinding.ItemCartBinding

class CartAdapter(private val list: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.VH>() {

    inner class VH(val binding: ItemCartBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]
        holder.binding.apply {
            tvName.text = item.product.name
            tvQty.text = "Qty: ${item.quantity}"
            tvPrice.text = "₹${item.product.price * item.quantity}"
        }

    }

    override fun getItemCount() = list.size
}
