package com.example.myfoodapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfoodapp.CartAdapter
import com.example.myfoodapp.MenuListAdapter
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private lateinit var adapter: MenuListAdapter
    private val allFoodNames =  listOf("Search Burger", "Sandwich", "Fish", "Adu", "Eggs", "Seggs")
    private val allFoodPrices = listOf("search 100k", "19.999d", "29$", "1 củ", "5K", "101")
    private val allFoodImages = listOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3
    )

    private val filterFoodName = mutableListOf<String>()
    private val filterFoodPrice = mutableListOf<String>()
    private val filterFoodImage = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        adapter = MenuListAdapter(filterFoodName, filterFoodPrice, filterFoodImage)
        binding.cycvSearch.layoutManager = LinearLayoutManager(requireContext())
        binding.cycvSearch.adapter = adapter

        setUpSearchView()

        showAllFood()
        return binding.root
    }

    private fun showAllFood() {
        filterFoodName.clear()
        filterFoodPrice.clear()
        filterFoodImage.clear()

        filterFoodName.addAll(allFoodNames)
        filterFoodPrice.addAll(allFoodPrices)
        filterFoodImage.addAll(allFoodImages)

        adapter.notifyDataSetChanged()
    }

    private fun setUpSearchView() {
        binding.searchviewSearchfrag.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                showItemSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                showItemSearch(newText)
                return true
            }

        })
    }

    private fun showItemSearch(query: String) {
        filterFoodName.clear()
        filterFoodPrice.clear()
        filterFoodImage.clear()

        allFoodNames.forEachIndexed { index, foodname ->
            if(foodname.contains(query, ignoreCase = true)){
                filterFoodName.add(foodname)
                filterFoodPrice.add(allFoodPrices[index])
                filterFoodImage.add(allFoodImages[index])
            }
        }
//        search for prices
//        allFoodPrices.forEachIndexed { index, foodPrice ->
//            if(foodPrice.contains(query, ignoreCase = true)){
//                filterFoodName.add(allFoodNames[index])
//                filterFoodPrice.add(foodPrice)
//                filterFoodImage.add(allFoodImages[index])
//            }
//        }

        adapter.notifyDataSetChanged()
    }

    companion object {
    }
}