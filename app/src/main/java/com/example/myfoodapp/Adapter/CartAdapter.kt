package com.example.myfoodapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfoodapp.databinding.FoodCartItemBinding

class CartAdapter(
    private val cartItem: MutableList<String>,
    private val cartItemPrice: MutableList<String>,
    private var cartImage: MutableList<Int>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private val itemQuantity = IntArray(cartItem.size) { 1 }

    inner class CartViewHolder(private val binding: FoodCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                txtCartItemName.text = cartItem[position]
                txtCartItemPrice.text = cartItemPrice[position]
                imgvCartitem.setImageResource(cartImage[position])
                val quantity = itemQuantity[position]
                txtSoluongCartItem.text = quantity.toString()

                btnGiamCartItem.setOnClickListener {
                    giamSoLuongItem(position)
                }

                btnTangCartItem.setOnClickListener {
                    tangSoLuongItem(position)
                }

                btnRemoveCartItem.setOnClickListener {
                    val itemPosition = adapterPosition
                    if(itemPosition != RecyclerView.NO_POSITION){
                        deleteItem(position)
                    }
                }

            }
        }

        private fun giamSoLuongItem(position: Int) {
            if (itemQuantity[position] > 1) {
                itemQuantity[position]--
                binding.txtSoluongCartItem.text = itemQuantity[position].toString()
            }
        }

        private fun tangSoLuongItem(position: Int) {
            if (itemQuantity[position] < 10) {
                itemQuantity[position]++
                binding.txtSoluongCartItem.text = itemQuantity[position].toString()
            }
        }

        private fun deleteItem(position: Int) {
            cartItem.removeAt(position)
            cartImage.removeAt(position)
            cartItemPrice.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItem.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            FoodCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartItem.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

}