package com.example.myfoodapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfoodapp.databinding.FoodHomeItemBinding

class AllFoodAdapter(
    private val items: List<String>,
    private val price: List<String>,
    private val image: List<Int>
) : RecyclerView.Adapter<AllFoodAdapter.AllFoodViewHolder>() {

    class AllFoodViewHolder(private val binding: FoodHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val imageview = binding.imgvFooditem
        fun bind(item: String, images: Int, price: String) {
            binding.txtNameFooditem.text = item
            binding.txtPriceFooditem.text = price
            imageview.setImageResource(images)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllFoodViewHolder {
        return AllFoodViewHolder(
            FoodHomeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AllFoodViewHolder, position: Int) {
        val item = items[position]
        val images = image[position]
        val price = price[position]
        holder.bind(item, images, price)
    }

}