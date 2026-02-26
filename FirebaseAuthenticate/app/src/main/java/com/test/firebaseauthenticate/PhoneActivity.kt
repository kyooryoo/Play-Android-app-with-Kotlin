package com.test.firebaseauthenticate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.test.firebaseauthenticate.databinding.ActivityPhoneBinding
import java.util.concurrent.TimeUnit

class PhoneActivity : AppCompatActivity() {

    lateinit var phoneBinding: ActivityPhoneBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        phoneBinding = ActivityPhoneBinding.inflate(layoutInflater)
        val view = phoneBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        phoneBinding.buttonSendOTP.setOnClickListener {
            val userPhone = phoneBinding.editTextPhone.text.toString()
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(userPhone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this@PhoneActivity)
                .setCallbacks(mCallbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }

        phoneBinding.buttonVerify.setOnClickListener {
            signinWithOTP()
        }

        mCallbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.d("PHONE","Verification Complete!")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.d("PHONE","Verification Failed!")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationCode = p0
            }

        }
    }

    fun signinWithOTP() {
        val userEnterCode = phoneBinding.editTextOTP.text.toString()
        val credential = PhoneAuthProvider.getCredential(verificationCode,userEnterCode)
        signinWithPhoneAuthCredentials(credential)
    }

    fun signinWithPhoneAuthCredentials(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this@PhoneActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext,"The code you entered is incorrect!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}