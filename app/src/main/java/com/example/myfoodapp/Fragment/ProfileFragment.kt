package com.example.myfoodapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.model.UserModel
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        setUserData()
        binding.btnInfoSavett.setOnClickListener {
            val name = binding.infoName.text.toString()
            val email = binding.infoEmail.text.toString()
            val address = binding.infoAddress.text.toString()
            val phone = binding.infoPhone.text.toString()
            updateUserData(name, email, address, phone)
        }

        binding.apply {
            infoName.isEnabled = false
            infoEmail.isEnabled = false
            infoAddress.isEnabled = false
            infoPhone.isEnabled = false
        }
        binding.btnEditProfile.setOnClickListener {
            binding.apply {
                infoName.isEnabled = !infoName.isEnabled
                infoEmail.isEnabled = !infoEmail.isEnabled
                infoAddress.isEnabled = !infoAddress.isEnabled
                infoPhone.isEnabled = !infoPhone.isEnabled
            }
        }

        return binding.root
    }

    private fun updateUserData(name: String, email: String, address: String, phone: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userReference = database.getReference("user").child(userId)
            val userData = hashMapOf(
                "name" to name,
                "email" to email,
                "address" to address,
                "phone" to phone,
            )
            userReference.setValue(userData).addOnSuccessListener {
                Toast.makeText(
                    requireContext(),
                    "Update thông tin thành công  !",
                    Toast.LENGTH_SHORT
                ).show()
            }
                .addOnFailureListener {
                    Toast.makeText(
                        requireContext(),
                        "Update không thành công  !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun setUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val useReference = database.getReference("user").child(userId)
            useReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val userProfile = snapshot.getValue(UserModel::class.java)
                        if (userProfile != null) {
                            binding.infoName.setText(userProfile.name)
                            binding.infoAddress.setText(userProfile.address)
                            binding.infoEmail.setText(userProfile.email)
                            binding.infoPhone.setText(userProfile.phone)

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    companion object {
    }
}