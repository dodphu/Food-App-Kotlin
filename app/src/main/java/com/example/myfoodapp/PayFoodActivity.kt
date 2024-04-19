package com.example.myfoodapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myfoodapp.databinding.ActivityPayFoodBinding

class PayFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayFoodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPayFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnDathangngayPayfood.setOnClickListener {
            val bottomDialogSheet = BuyFoodBottomSheetFragment()
            bottomDialogSheet.show(supportFragmentManager, "buyfoodbottom")
        }
    }
}