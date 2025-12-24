package com.example.shoppingcart.ui.cart

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.shoppingcart.R
import com.example.shoppingcart.databinding.FragmentCartBinding
import com.example.shoppingcart.databinding.LayoutCouponBinding
import com.example.shoppingcart.ui.MainActivity
import com.example.shoppingcart.ui.checkout.CheckoutFragment
import com.example.shoppingcart.viewmodel.CartViewModel

class CartFragment : Fragment(R.layout.fragment_cart) {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val vm: CartViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentCartBinding.bind(view)

        // ✅ Inflate coupon layout manually
        val couponBinding = LayoutCouponBinding.inflate(
            layoutInflater,
            binding.couponContainer,
            true
        )

        val tvDiscount = binding.tvDiscount

        binding.rvCart.layoutManager =
            LinearLayoutManager(requireContext())

        vm.cartItems.observe(viewLifecycleOwner) { list ->
            binding.rvCart.adapter = CartAdapter(list)

            binding.tvSubtotal.text = "Subtotal: ₹${vm.subtotal()}"
            binding.tvTax.text = "Tax: ₹${vm.tax()}"
            binding.tvDiscount.text = "Discount: -₹${vm.discount()}"
            binding.tvFinal.text = "Final Amount: ₹${vm.finalAmount()}"
        }

        // Enable / Disable coupon button
        vm.isCouponEnabled.observe(viewLifecycleOwner) { enabled ->
            couponBinding.btnApplyCoupon.isEnabled = enabled
        }

        // Apply coupon click
        couponBinding.btnApplyCoupon.setOnClickListener {
            vm.applyCoupon()
        }

        // Observe coupon applied
        vm.isCouponApplied.observe(viewLifecycleOwner) { applied ->
            if (applied) {
                couponBinding.btnApplyCoupon.text = "APPLIED"
                couponBinding.btnApplyCoupon.isEnabled = false
            }
            updatePriceSummary(tvDiscount)
        }

        vm.cartItems.observe(viewLifecycleOwner) {
            updatePriceSummary(tvDiscount)
        }

        binding.btnCheckout.setOnClickListener {
            (activity as MainActivity).openFragment(CheckoutFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updatePriceSummary(tvDiscount: TextView) {
        tvDiscount.text = "Discount: ₹${vm.discount()}"
        binding.tvFinal.text = "Final Amount: ₹${vm.finalAmount()}"
    }

}
