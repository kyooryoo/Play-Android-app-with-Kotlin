package com.test.firebaseauthenticate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.test.firebaseauthenticate.databinding.ActivityLoginBinding
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginBinding.buttonSignin.setOnClickListener {
            val userEmail = loginBinding.editTextEmailSignin.text.toString()
            val userPassword = loginBinding.editTextPasswordSignin.text.toString()
            signinWithFirebase(userEmail, userPassword)
        }

        loginBinding.buttonSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }

        loginBinding.buttonForgot.setOnClickListener {
            val intent = Intent(this,ForgetActivity::class.java)
            startActivity(intent)
        }

        loginBinding.buttonSigninPhone.setOnClickListener {
            val intent = Intent(this, PhoneActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun signinWithFirebase (userEmail: String, userPassword: String) {
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext,"Login is successful!",Toast.LENGTH_LONG).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext,task.exception?.toString(),Toast.LENGTH_SHORT).show()
                }
            }
    }

    // remember the singed in user as long as the app is active and the user does not sign out
    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}