package com.example.shoppingcart.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingcart.R
import com.example.shoppingcart.data.model.Product
import com.example.shoppingcart.databinding.FragmentProductListBinding
import com.example.shoppingcart.ui.MainActivity
import com.example.shoppingcart.ui.cart.CartFragment
import com.example.shoppingcart.viewmodel.CartViewModel

class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val vm: CartViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentProductListBinding.bind(view)

        val products = listOf(
            Product(1, "Shoes", 1500.0, null, 18),
            Product(2, "T-Shirt", 800.0, 1000.0, 5),
            Product(3, "Jeans", 2000.0, null, 18),
            Product(4, "Cap", 400.0, null, 5),
            Product(5, "Jacket", 2500.0, 3000.0, 18),
            Product(6, "HandBag", 2500.0, 3000.0, 18),
            Product(7, "Wallet", 2000.0, 2500.0, 18)
        )

        binding.rvProducts.layoutManager =
            LinearLayoutManager(requireContext())
        binding.rvProducts.adapter =
            ProductAdapter(products) { vm.addToCart(it) }

        binding.btnViewCart.setOnClickListener {
            (activity as MainActivity)
                .openFragment(CartFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
