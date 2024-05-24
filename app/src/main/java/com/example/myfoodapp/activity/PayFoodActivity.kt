package com.example.myfoodapp.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myfoodapp.R
import com.example.myfoodapp.bottomSheet.BuyFoodBottomSheetFragment
import com.example.myfoodapp.model.OrderDetails
import com.example.myfoodapp.databinding.ActivityPayFoodBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PayFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayFoodBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var name_tt: String
    private lateinit var address_tt: String
    private lateinit var phone_tt: String
    private lateinit var totalAmount: String
    private lateinit var foodItemName: ArrayList<String>
    private lateinit var foodItemPrice: ArrayList<String>
    private lateinit var foodItemImage: ArrayList<String>
    private lateinit var foodItemDescription: ArrayList<String>
    private lateinit var foodItemIngredient: ArrayList<String>
    private lateinit var foodItemQuantites: ArrayList<Int>

    private lateinit var databaseReference: DatabaseReference
    private lateinit var userId: String

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

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference()
        setUserData()
        val intent = intent
        foodItemName = intent.getStringArrayListExtra("FoodItemName") as ArrayList<String>
        foodItemPrice = intent.getStringArrayListExtra("FoodItemPrice") as ArrayList<String>
        foodItemImage = intent.getStringArrayListExtra("FoodItemImage") as ArrayList<String>
        foodItemDescription =
            intent.getStringArrayListExtra("FoodItemDescription") as ArrayList<String>
        foodItemIngredient =
            intent.getStringArrayListExtra("FoodItemIngredient") as ArrayList<String>
        foodItemQuantites = intent.getIntegerArrayListExtra("FoodItemQuantites") as ArrayList<Int>

        totalAmount = calculateTotalAmount().toString() + "đ"
        binding.txtThongtindonhangTongtientra.isEnabled = false
        binding.txtThongtindonhangTongtientra.setText(totalAmount)

        binding.btnDathangngayPayfood.setOnClickListener {
            name_tt = binding.txtThongtindonhangName.text.toString().trim()
            address_tt = binding.txtThongtindonhangAddress.text.toString().trim()
            phone_tt = binding.txtThongtindonhangSdt.text.toString().trim()
            if (name_tt.isBlank() && address_tt.isBlank() && phone_tt.isBlank()) {
                Toast.makeText(this, "Hãy điền đầy đủ thông tin !", Toast.LENGTH_SHORT).show()
            } else {
                placeOrder()
            }

        }
    }

    private fun placeOrder() {
        userId = auth.currentUser?.uid ?: ""
        val time = System.currentTimeMillis()
        val itemPushKey = databaseReference.child("OrderDetails").push().key
        val orderDetails = OrderDetails(
            userId,
            name_tt,
            foodItemName,
            foodItemPrice,
            foodItemImage,
            foodItemQuantites,
            address_tt,
            totalAmount,
            phone_tt,
            time,
            itemPushKey,
            false,
            false
        )
        val orderReference = databaseReference.child("OrderDetails").child(itemPushKey!!)
        orderReference.setValue(orderDetails).addOnSuccessListener {
            val bottomDialogSheet = BuyFoodBottomSheetFragment()
            bottomDialogSheet.show(supportFragmentManager, "buyfoodbottom")
            removeItemFromCart()
            addOrderToHistory(orderDetails)
        }
            .addOnFailureListener{
                Toast.makeText(this,"Order không thành công !", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addOrderToHistory(orderDetails: OrderDetails) {
        databaseReference.child("user").child(userId).child("BuyHistory")
            .child(orderDetails.itemPushKey!!).setValue(orderDetails).addOnSuccessListener {

            }
    }

    private fun removeItemFromCart() {
        val cartItemsReference = databaseReference.child("user").child(userId).child("CartItems")
        cartItemsReference.removeValue()
    }

    private fun calculateTotalAmount(): Int {
        var totalAmount = 0
        for (i in 0 until foodItemPrice.size) {
            var price = foodItemPrice[i]
            val lastChar = price.last()
            val priceIntVale = if (lastChar == 'đ') {
                price.dropLast(1).toInt()
            } else {
                price.toInt()
            }
            var quantity = foodItemQuantites[i]
            totalAmount += priceIntVale * quantity
        }
        return totalAmount
    }

    private fun setUserData() {
        val user = auth.currentUser
        if (user != null) {
            val userId = user.uid
            val userReference = databaseReference.child("user").child(userId)
            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val name = snapshot.child("name").getValue(String::class.java) ?: ""
                        val address = snapshot.child("address").getValue(String::class.java) ?: ""
                        val phone = snapshot.child("address").getValue(String::class.java) ?: ""
                        binding.apply {
                            txtThongtindonhangName.setText(name)
                            txtThongtindonhangAddress.setText(address)
                            txtThongtindonhangSdt.setText(phone)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}