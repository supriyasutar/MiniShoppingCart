package com.example.shoppingcart.data.model

/**
 * Represents a product added to cart with quantity
 */
data class CartItem(
    val product: Product,
    var quantity: Int = 1
)