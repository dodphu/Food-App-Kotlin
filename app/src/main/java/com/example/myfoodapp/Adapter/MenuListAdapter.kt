package com.example.myfoodapp.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.model.MenuItem
import com.example.myfoodapp.DetailActivity
import com.example.myfoodapp.databinding.MenuListItemBinding

class MenuListAdapter(
    private val menuItems: List<MenuItem>,
    private val requireContext: Context,
) : RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder>() {
    private val itemClickListener: OnItemClickListener? = null
    inner class MenuListViewHolder(private val binding: MenuListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(position)
                }
                openDetailActivity(position)
            }
        }

        private fun openDetailActivity(position: Int) {
            val menuItem = menuItems[position]
            val intent = Intent(requireContext,DetailActivity::class.java).apply {
                putExtra("MenuItemName", menuItem.foodName)
                putExtra("MenuItemPrice", menuItem.foodPrice)
                putExtra("MenuItemDescription", menuItem.foodDescription)
                putExtra("MenuItemImage", menuItem.foodImage)
                putExtra("MenuItemIngredients", menuItem.foodThanhPhan)
            }
            requireContext.startActivity(intent)
        }
        fun bind(position: Int) {
            val menuItem = menuItems[position]
            binding.apply {
                txtNameFooditemMenu.text = menuItem.foodName
                txtPriceFooditemMenu.text = menuItem.foodPrice
                val foodImageUrl = menuItem.foodImage ?: "default_image_url"

                val uri = Uri.parse(foodImageUrl)
                Glide.with(requireContext).load(uri).into(imgvFooditemMenu)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListViewHolder {
        val binding = MenuListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) {
        holder.bind(position)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
