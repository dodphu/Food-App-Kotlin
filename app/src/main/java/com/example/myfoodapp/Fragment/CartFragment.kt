package com.example.myfoodapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfoodapp.CartAdapter
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        //sample data
        val cartFoodName = listOf("Burger", "Sandwich", "Fish", "Adu", "Eggs", "Seggs")
        val cartItemPrice = listOf("100k", "19.999d", "29$", "1 củ", "5K", "101")
        val cartImage = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3
        )
        val adapter = CartAdapter(ArrayList(cartFoodName),ArrayList(cartItemPrice),ArrayList(cartImage))
        binding.rycvCartfrag.layoutManager = LinearLayoutManager(requireContext())
        binding.rycvCartfrag.adapter = adapter

        return binding.root
    }

    companion object {
    }
}