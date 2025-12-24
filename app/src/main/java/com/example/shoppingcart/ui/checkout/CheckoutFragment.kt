package com.example.shoppingcart.ui.checkout

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shoppingcart.R
import com.example.shoppingcart.databinding.FragmentCheckoutBinding
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class CheckoutFragment : Fragment(R.layout.fragment_checkout) {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentCheckoutBinding.bind(view)

            binding.konfettiView.start(
                Party(
                    speed = 0f,
                    maxSpeed = 30f,
                    damping = 0.9f,
                    spread = 360,
                    colors = listOf(Color.YELLOW, Color.GREEN, Color.MAGENTA),
                    emitter = Emitter(duration = 2, TimeUnit.SECONDS).perSecond(100)
                )
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
