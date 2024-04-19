package com.example.myfoodapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfoodapp.Adapter.BuyAgainAdapter
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var buyAgainAdapter: BuyAgainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        //sample data
        val buyAgainFoodName = arrayListOf("Tao", "Chuoi", "Food3")
        val buyAgainFoodPrice = arrayListOf("10k", "2000", "1$")
        val buyAgainFoodImage = arrayListOf(R.drawable.menu1, R.drawable.menu2, R.drawable.menu3)
        buyAgainAdapter = BuyAgainAdapter(buyAgainFoodName, buyAgainFoodPrice, buyAgainFoodImage)
        binding.rycvHistoryfrag.adapter = buyAgainAdapter
        binding.rycvHistoryfrag.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
    }
}