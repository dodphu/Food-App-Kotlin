package com.example.myfoodapp.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfoodapp.R
import com.example.myfoodapp.model.OrderDetails
import com.example.myfoodapp.adapter.RecentBuyAdapter
import com.example.myfoodapp.databinding.ActivityRecentOrderItemBinding

class RecentOrderItemActivity : AppCompatActivity() {
    private val binding: ActivityRecentOrderItemBinding by lazy {
        ActivityRecentOrderItemBinding.inflate(layoutInflater)
    }
    private lateinit var allFoodNames: ArrayList<String>
    private lateinit var allFoodImages: ArrayList<String>
    private lateinit var allFoodPrices: ArrayList<String>
    private lateinit var allFoodQuantities: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recentOrderItem = intent.getSerializableExtra("RecentBuyOrderItem") as ArrayList<OrderDetails>
        recentOrderItem?.let { orderDetail ->
            if (orderDetail.isNotEmpty()) {
                val recentOrderItem = orderDetail[0]
                allFoodNames = recentOrderItem.foodNames as ArrayList<String>
                allFoodImages = recentOrderItem.foodImages as ArrayList<String>
                allFoodPrices = recentOrderItem.foodPrices as ArrayList<String>
                allFoodQuantities = recentOrderItem.foodQuantities as ArrayList<Int>

            }
        }
        setAdapter()
    }

    private fun setAdapter() {
        val rv = binding.rycvRecentBuy
        rv.layoutManager = LinearLayoutManager(this)
        val adapter =
            RecentBuyAdapter(this, allFoodNames, allFoodImages, allFoodPrices, allFoodQuantities)
        rv.adapter = adapter
    }
}