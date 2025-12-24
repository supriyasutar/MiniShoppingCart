package com.example.shoppingcart.data.repository

import com.example.shoppingcart.data.model.CartItem
import com.example.shoppingcart.data.model.Product
import com.example.shoppingcart.utils.Constants

class CartRepository {

    // In-memory cart storage
    private val cartItems = mutableListOf<CartItem>()

    // -------------------------
    // CART OPERATIONS
    // -------------------------

    fun addItem(product: Product) {
        val existing = cartItems.find { it.product.id == product.id }
        if (existing != null) {
            existing.quantity++
        } else {
            cartItems.add(CartItem(product))
        }
    }

    fun getCartItems(): List<CartItem> = cartItems

    // -------------------------
    // PRICE CALCULATIONS
    // -------------------------

    /**
     * Subtotal = sum of (price * quantity)
     */
    fun getSubtotal(): Double {
        return cartItems.sumOf {
            it.product.price * it.quantity
        }
    }

    /**
     * Tax total considering mixed tax slabs
     */
    fun getTaxTotal(): Double {
        return cartItems.sumOf {
            val taxRate = it.product.taxPercent / 100.0
            it.product.price * it.quantity * taxRate
        }
    }

    // -------------------------
    // COUPON LOGIC
    // -------------------------

    /**
     * Checks if coupon can be enabled
     * (Used by ViewModel to enable APPLY button)
     */
    fun isCouponEligible(): Boolean {
        // Rule 1: minimum cart value
        if (getSubtotal() < Constants.MIN_CART_VALUE) {
            return false
        }

        // If there are 1 or 1+ non discounted items present in the cart then enable the coupon
        return cartItems.any { it.product.originalPrice == null }
    }

    /**
     * Calculates coupon discount based on rules:
     * - Min cart value ₹1000
     * - 20% discount
     * - Max ₹300
     * - Exclude discounted items
     */
    fun getCouponDiscount(): Double {

        // Rule 1: minimum cart value
        if (!isCouponEligible()) {
            return 0.0
        }

        // Rule 4: exclude discounted items
        val eligibleAmount = cartItems
            .filter { it.product.originalPrice == null }
            .sumOf { it.product.price * it.quantity }

        if (eligibleAmount <= 0) {
            return 0.0
        }

        // Rule 2 & 3: percentage + cap
        val discount = eligibleAmount * Constants.DISCOUNT_PERCENT
        return minOf(discount, Constants.MAX_DISCOUNT)
    }

    /**
     * Final payable amount
     */
    fun getFinalAmount(isCouponApplied: Boolean): Double {
        val discount = if (isCouponApplied) getCouponDiscount() else 0.0
        return getSubtotal() + getTaxTotal() - discount
    }
}

