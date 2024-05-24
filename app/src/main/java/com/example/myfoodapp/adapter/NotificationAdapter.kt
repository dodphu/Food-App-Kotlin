package com.example.myfoodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfoodapp.databinding.NotificationItemBinding

class NotificationAdapter(private var notifi: ArrayList<String>, private var notiImage: ArrayList<Int> ): RecyclerView.Adapter<NotificationAdapter.NotiViewHolder>() {
    inner class NotiViewHolder(private val binding: NotificationItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.txtNoti.text = notifi[position]
            binding.imgvNotifiItem.setImageResource(notiImage[position])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotiViewHolder {
        val binding = NotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return NotiViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notifi.size
    }

    override fun onBindViewHolder(holder: NotiViewHolder, position: Int) {
        holder.bind(position)
    }
}