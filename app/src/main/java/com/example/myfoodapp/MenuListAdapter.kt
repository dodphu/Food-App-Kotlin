package com.example.myfoodapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfoodapp.databinding.MenuListItemBinding

class MenuListAdapter(private val menuItemsName: MutableList<String>, private val menuItemPrice: MutableList<String>, private val menuImage: MutableList<Int>) : RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder>() {
    inner class MenuListViewHolder(private val binding: MenuListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                txtNameFooditemMenu.text = menuItemsName[position]
                txtPriceFooditemMenu.text = menuItemPrice[position]
                imgvFooditemMenu.setImageResource(menuImage[position])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListViewHolder {
        val binding = MenuListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuItemsName.size
    }

    override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) {
        holder.bind(position)
    }
}