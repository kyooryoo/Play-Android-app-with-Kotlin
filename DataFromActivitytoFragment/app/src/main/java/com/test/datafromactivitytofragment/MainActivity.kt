package com.test.datafromactivitytofragment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.nio.BufferUnderflowException

class MainActivity : AppCompatActivity() {

    lateinit var etWeight: EditText
    lateinit var etHeight: EditText
    lateinit var calculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etWeight = findViewById(R.id.editTextWeight)
        etHeight = findViewById(R.id.editTextHeight)
        calculate = findViewById(R.id.buttonCalculate)

        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        val bmiFragment = BmiFragment()

        calculate.setOnClickListener {
            // use bundle to send data from activity to fragment
            val bundle = Bundle()
            val weight = etWeight.text.toString().toInt()
            val height = etHeight.text.toString().toInt()
            bundle.putInt("weight", weight)
            bundle.putInt("height", height)

            bmiFragment.arguments = bundle
            ft.add(R.id.frame, bmiFragment)
            ft.commit()
        }
    }
}