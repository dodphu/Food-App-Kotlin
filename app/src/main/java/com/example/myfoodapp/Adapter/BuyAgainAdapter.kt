package com.example.myfoodapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfoodapp.databinding.HistoryBuyAgainItemBinding

class BuyAgainAdapter(
    private val buyAgainFoodName: ArrayList<String>,
    private val buyAgainFoodPrice: ArrayList<String>,
    private val buyAgainFoodImage: ArrayList<Int>
) : RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {
    class BuyAgainViewHolder(private val binding: HistoryBuyAgainItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(foodname: String, foodprice: String, foodimage: Int) {
            binding.txtNameFooditemBuyagain.text = foodname
            binding.txtPriceFooditemBuyagain.text = foodprice
            binding.imgvFooditemBuyagain.setImageResource(foodimage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {
        val binding = HistoryBuyAgainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyAgainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return buyAgainFoodName.size
    }

    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        holder.bind(buyAgainFoodName[position], buyAgainFoodPrice[position], buyAgainFoodImage[position])
    }
}