package com.test.firebaseauthenticate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.test.firebaseauthenticate.databinding.ActivitySignupBinding
import kotlin.math.sign

class SignupActivity : AppCompatActivity() {

    lateinit var signupBinding: ActivitySignupBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        signupBinding.buttonSignup.setOnClickListener {
            val userEmail = signupBinding.editTextEmailSignup.text.toString()
            val userPassword = signupBinding.editTextPasswordSignup.text.toString()
            signupWithFirebase(userEmail, userPassword)
        }
    }

    fun signupWithFirebase(userEmail: String, userPassword: String) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Your account is created!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception?.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}