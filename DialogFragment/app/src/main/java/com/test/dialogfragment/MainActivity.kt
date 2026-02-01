package com.test.dialogfragment

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    lateinit var show: Button
    lateinit var name: TextView
    lateinit var age: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        show = findViewById(R.id.buttonShow)
        name = findViewById(R.id.textViewName)
        age = findViewById(R.id.textViewAge)

        show.setOnClickListener {
            val fragmentManager: FragmentManager = supportFragmentManager
            val dialogFragment = DialogFragment()
            dialogFragment.isCancelable = false
            dialogFragment.show(fragmentManager, "DialogFragment")
        }
    }

    fun getUserData(userName: String, userAge: Int) {
        "Name: $userName".also { name.text = it }
        "Age: $userAge".also { age.text = it }
    }
}