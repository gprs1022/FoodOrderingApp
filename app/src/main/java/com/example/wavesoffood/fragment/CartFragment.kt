package com.example.wavesoffood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.R
import com.example.wavesoffood.adapter.CartAdapter
import com.example.wavesoffood.databinding.FragmentCartBinding

class CartFragment : Fragment() {
 private lateinit var  binding : FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)

        val cartFoodName = listOf("Burger","sandwich","Tea","sandwich","momo")
        val cartItemPrice = listOf("$5","$7","$8","$10","$10")
        val cartImage = listOf(R.drawable.roll,R.drawable.ice,R.drawable.tea,R.drawable.f3,R.drawable.f4)
        val adapter = CartAdapter(ArrayList(cartFoodName),ArrayList(cartItemPrice),ArrayList(cartImage))
        binding.CartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.CartRecyclerView.adapter = adapter
       return  binding.root
    }

    companion object {


    }
}