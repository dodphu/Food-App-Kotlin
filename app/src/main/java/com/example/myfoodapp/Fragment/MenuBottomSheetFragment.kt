package com.example.myfoodapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfoodapp.CartAdapter
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheetBinding.inflate(layoutInflater, container, false)
        //sample data
        val menuFoodName = listOf("Burger", "Sandwich", "Fish", "Adu", "Eggs", "Seggs")
        val menuItemPrice = listOf("100k", "19.999d", "29$", "1 củ", "5K", "101")
        val menuImage = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3
        )
        val adapter = CartAdapter(ArrayList(menuFoodName),ArrayList(menuItemPrice),ArrayList(menuImage))
        binding.rycvMenuBottomSheet.layoutManager = LinearLayoutManager(requireContext())
        binding.rycvMenuBottomSheet.adapter = adapter


        return binding.root
    }

    companion object {
    }
}