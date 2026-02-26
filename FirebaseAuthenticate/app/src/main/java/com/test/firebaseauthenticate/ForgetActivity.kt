package com.test.firebaseauthenticate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.test.firebaseauthenticate.databinding.ActivityForgetBinding

class ForgetActivity : AppCompatActivity() {

    lateinit var forgetBinding: ActivityForgetBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        forgetBinding = ActivityForgetBinding.inflate(layoutInflater)
        val view = forgetBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        forgetBinding.buttonReset.setOnClickListener {
            val email = forgetBinding.editTextReset.text.toString()
            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Password reset email is sent", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}