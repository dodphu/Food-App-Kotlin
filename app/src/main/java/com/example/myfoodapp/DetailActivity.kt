package com.example.myfoodapp

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.model.CartItem
import com.example.myfoodapp.databinding.ActivityDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailActivity : AppCompatActivity() {
    private var foodName: String? = null
    private var foodImage: String? = null
    private var foodDesription: String? = null
    private var foodIngredient: String? = null
    private var foodPrice: String? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()
        foodName = intent.getStringExtra("MenuItemName")
        foodDesription = intent.getStringExtra("MenuItemDescription")
        foodIngredient = intent.getStringExtra("MenuItemIngredients")
        foodPrice = intent.getStringExtra("MenuItemPrice")
        foodImage = intent.getStringExtra("MenuItemImage")

        binding.apply {
            txtFoodnameDetail.text = foodName
            txtMotaDetail.text = foodDesription
            txtThanhphanDetail.text = foodIngredient

            Glide.with(this@DetailActivity).load(Uri.parse(foodImage)).into(imgvFoodDetail)

        }

        binding.btnAddcartDetail.setOnClickListener {
            addItemtoCart()
        }
    }

    private fun addItemtoCart() {
        val database = FirebaseDatabase.getInstance().reference
        val userId = auth.currentUser?.uid ?: ""

        val cartItem = CartItem(
            foodName.toString(),
            foodPrice.toString(),
            foodDesription.toString(),
            foodImage.toString(),
            1,
            foodIngredient.toString()
        )

        database.child("user").child(userId).child("cartItem").push().setValue(cartItem)
            .addOnSuccessListener {
                Toast.makeText(this, "Thêm item vào giỏ hàng thành công !", Toast.LENGTH_SHORT)
                    .show()
            }.addOnFailureListener {
                Toast.makeText(
                    this,
                    "Thêm item vào giỏ hàng không thành công !",
                    Toast.LENGTH_SHORT
                ).show()

            }
    }
}