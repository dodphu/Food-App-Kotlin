package com.example.myfoodapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.model.MenuItem
import com.example.myfoodapp.Adapter.CartAdapter
import com.example.myfoodapp.Adapter.MenuListAdapter
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var menuItem: MutableList<MenuItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheetBinding.inflate(layoutInflater, container, false)

        retrieveMenuItem()
        return binding.root
    }

    private fun retrieveMenuItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef = database.reference.child("menu")
        menuItem = mutableListOf()
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snapshotItem in snapshot.children) {
                    val itemmenu = snapshotItem.getValue(MenuItem::class.java)
                    itemmenu?.let {
                        menuItem.add(it)
                    }
                    Log.d("phuu", "onDataChange: ${menuItem}")
                }
                setAdapter()
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setAdapter() {
        if (menuItem.isNotEmpty()) {
            val adapter = MenuListAdapter(menuItem, requireContext())
            binding.rycvMenuBottomSheet.layoutManager = LinearLayoutManager(requireContext())
            binding.rycvMenuBottomSheet.adapter = adapter
        } else {
            Log.d("phu", "setAdapter: Data not found")
        }
    }

    companion object {
    }
}