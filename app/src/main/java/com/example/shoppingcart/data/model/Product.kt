package com.example.shoppingcart.data.model

/**
 * Represents a product shown in the product list
 */
data class Product(
    val id: Int,
    val name: String,
    val price: Double,          // Selling price
    val originalPrice: Double?, // Null if not discounted
    val taxPercent: Int,        // 5 or 18
    val imageRes: Int   = 0         // Drawable resource
)