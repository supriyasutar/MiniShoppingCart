package com.example.shoppingcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.data.model.CartItem
import com.example.shoppingcart.data.model.Product
import com.example.shoppingcart.data.repository.CartRepository

/**
 * Shared ViewModel across fragments
 */
class CartViewModel : ViewModel() {

    private val repo = CartRepository()

    val cartItems = MutableLiveData<List<CartItem>>()

    val isCouponEnabled = MutableLiveData(false)
    val isCouponApplied = MutableLiveData(false)

    fun addToCart(product: Product) {
        repo.addItem(product)
        cartItems.value = repo.getCartItems()
        isCouponEnabled.value = repo.isCouponEligible()
    }

    fun applyCoupon() {
        if (isCouponEnabled.value == true) {
            isCouponApplied.value = true
        }
    }

    fun subtotal() = repo.getSubtotal()
    fun tax() = repo.getTaxTotal()
    fun discount() =
        if (isCouponApplied.value == true) repo.getCouponDiscount() else 0.0

    fun finalAmount() =
        repo.getFinalAmount(isCouponApplied.value == true)
}

