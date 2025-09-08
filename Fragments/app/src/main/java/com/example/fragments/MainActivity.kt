package com.example.fragments

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    lateinit var replace : Button
    lateinit var activity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replace = findViewById(R.id.buttonSecondFragment)
        activity = findViewById(R.id.buttonSecondActivity)


        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val firstFragment = FirstFragment()
        fragmentTransaction.add(R.id.frame, firstFragment)
        fragmentTransaction.commit()

        replace.setOnClickListener {
            val secondFragmentManager : FragmentManager = supportFragmentManager
            val secondFragmentTransaction : FragmentTransaction = secondFragmentManager.beginTransaction()
            val secondFragment = SecondFragment()
            secondFragmentTransaction.replace(R.id.frame, secondFragment)
            secondFragmentTransaction.addToBackStack(null)
            secondFragmentTransaction.commit()
        }

        activity.setOnClickListener {
            val fragmentManager : FragmentManager = supportFragmentManager
            val dialogFragment = DialogFragment()

            dialogFragment.isCancelable = false

            dialogFragment.show(fragmentManager, "DialogFragment")
        }
    }
}