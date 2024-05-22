package com.example.myfoodapp.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.model.MenuItem
import com.example.myfoodapp.Adapter.MenuListAdapter
import com.example.myfoodapp.MenuBottomSheetFragment
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {
    private lateinit var database: FirebaseDatabase
    private lateinit var menuItem: MutableList<MenuItem>
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnViewmenu.setOnClickListener {
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager, "BottomSheet")
        }
        retriveAndDisplayFoodItem()
        return binding.root

    }

    private fun retriveAndDisplayFoodItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef = database.reference.child("menu")
        menuItem = mutableListOf()

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    val itemmenu = foodSnapshot.getValue(MenuItem::class.java)
                    Log.d("phuzzj", "onDataChange: $itemmenu")
                    itemmenu?.let {
                        menuItem.add(it)
                    }
                }
                // Gọi hàm randomFoodItem() sau khi đã lấy được dữ liệu thành công
                randomFoodItem()
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý lỗi nếu có
                Log.e("TAG", "Failed to retrieve data from Firebase", error.toException())
            }
        })
    }


    private fun randomFoodItem() {
        val index = menuItem.indices.toList().shuffled()
        val numItemshow = 6
        val subsetMenuItem = index.take(numItemshow).map { menuItem[it] }
        setItemAdapter(subsetMenuItem)
    }

    private fun setItemAdapter(subsetMenuItem: List<MenuItem>) {
        val adapter = MenuListAdapter(subsetMenuItem, requireContext())
        binding.rycvHomefrag.layoutManager = LinearLayoutManager(requireContext())
        binding.rycvHomefrag.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                val itemPosition = imageList[position]
                val itemMessage = "vừa click vào item vị trí $position"
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val itemMessage = "vừa click vào item vị trí $position"
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }

    companion object {

    }
}