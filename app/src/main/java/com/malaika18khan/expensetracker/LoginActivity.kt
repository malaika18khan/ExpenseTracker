package com.malaika18khan.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.malaika18khan.expensetracker.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener{

            val email = binding.emailLpBtn.text.toString()
            val password = binding.passwordLpBtn.text.toString()

            if (email.isEmpty()){
                binding.emailLpBtn.error = "Please enter your email"
                return@setOnClickListener
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.emailLpBtn.error = "Please enter valid email"
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.passwordLpBtn.error = "Please enter your password"
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Login successfull", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else {
                        Toast.makeText(this, "Login failed: Please check your credentials", Toast.LENGTH_SHORT).show()
                    }

                }

        }
    }
}