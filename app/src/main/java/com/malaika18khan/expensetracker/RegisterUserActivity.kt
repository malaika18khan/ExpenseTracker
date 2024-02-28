package com.malaika18khan.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.malaika18khan.expensetracker.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            if (name.isEmpty()){
                binding.editTextName.error = "Please enter your name"
                return@setOnClickListener
            }

            if (email.isEmpty()){
                binding.editTextTextEmailAddress.error = "Please enter your email"
                return@setOnClickListener
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editTextTextEmailAddress.error = "Please enter valid email"
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.editTextTextPassword.error = "Please enter your password"
                return@setOnClickListener
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Signup successfull", Toast.LENGTH_SHORT).show()
                        finish()
                    } else{
                        Toast.makeText(this, "Signup failed", Toast.LENGTH_SHORT).show()
                    }
                }




        }

    }
}