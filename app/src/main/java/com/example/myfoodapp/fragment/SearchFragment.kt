package com.example.myfoodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfoodapp.model.MenuItem
import com.example.myfoodapp.adapter.MenuListAdapter
import com.example.myfoodapp.databinding.FragmentSearchBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuListAdapter
    private lateinit var database: FirebaseDatabase
    private val originalMenuItems = mutableListOf<MenuItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        //retrive menu item from database
        retrieveMenuItems()

        setUpSearchView()

        return binding.root
    }

    private fun retrieveMenuItems() {
        //get database reference
        database = FirebaseDatabase.getInstance()

        val foodReference: DatabaseReference = database.reference.child("menu")
        foodReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    val menuItem = foodSnapshot.getValue(MenuItem::class.java)
                    menuItem?.let {
                        originalMenuItems.add(it)
                    }
                }
                showAllMenu()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun showAllMenu() {
        val filterMenuItem = ArrayList(originalMenuItems)
        setUpAdapter(filterMenuItem)
    }

    private fun setUpAdapter(filterMenuItem: List<MenuItem>) {
        adapter = MenuListAdapter(filterMenuItem, requireContext())
        binding.cycvSearch.layoutManager = LinearLayoutManager(requireContext())
        binding.cycvSearch.adapter = adapter
    }


    private fun setUpSearchView() {
        binding.searchviewSearchfrag.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
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
        val filterMenuItem = originalMenuItems.filter {
            it.foodName?.contains(query, ignoreCase = true) == true
        }
        setUpAdapter(filterMenuItem)

//        search for prices
//        allFoodPrices.forEachIndexed { index, foodPrice ->
//            if(foodPrice.contains(query, ignoreCase = true)){
//                filterFoodName.add(allFoodNames[index])
//                filterFoodPrice.add(foodPrice)
//                filterFoodImage.add(allFoodImages[index])
//            }
//        }

    }

    companion object {
    }
}