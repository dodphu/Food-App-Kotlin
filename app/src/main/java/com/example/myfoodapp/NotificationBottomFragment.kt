package com.example.myfoodapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfoodapp.Adapter.NotificationAdapter
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentNotificationBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NotificationBottomFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNotificationBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBottomBinding.inflate(layoutInflater, container, false)
        val notificationMy = listOf(
            "Đơn hàng order được hủy thành công !",
            "Đơn hàng đang được Shipper vận chuyển đến bạn !",
            " Đơn hàng đã được giao thành công !"
        )
        val notificationImage = listOf(R.drawable.cancelorder,R.drawable.giaohang,R.drawable.ordercomplete)
        val adapter = NotificationAdapter(ArrayList(notificationMy) , ArrayList(notificationImage))
        binding.rycvNotifi.layoutManager = LinearLayoutManager(requireContext())
        binding.rycvNotifi.adapter = adapter

        return binding.root
    }

    companion object {

    }

}