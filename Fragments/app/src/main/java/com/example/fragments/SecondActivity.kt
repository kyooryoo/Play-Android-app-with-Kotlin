package com.example.fragments

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Receive the passed in position argument from the first fragment
        val position = intent.getIntExtra("position", 0)

        // Register the callback for handling back presses
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@SecondActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })

        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val secondFragment = SecondFragment()

        // Use bundle to send the position argument to the second fragment
        val bundle = Bundle()
        bundle.putInt("position", position)
        secondFragment.arguments = bundle

        fragmentTransaction.add(R.id.frameLayout, secondFragment)
        fragmentTransaction.commit()
    }
}