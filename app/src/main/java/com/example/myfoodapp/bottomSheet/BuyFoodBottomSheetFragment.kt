package com.example.myfoodapp.bottomSheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfoodapp.activity.MainActivity
import com.example.myfoodapp.databinding.FragmentBuyFoodBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BuyFoodBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBuyFoodBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBuyFoodBottomSheetBinding.inflate(layoutInflater, container, false)

        binding.btnBackBuyfoodbottomsheet.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object {

    }
}