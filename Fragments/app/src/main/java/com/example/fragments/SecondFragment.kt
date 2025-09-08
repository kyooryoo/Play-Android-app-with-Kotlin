package com.example.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    lateinit var imageView: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_second, container, false)

        // Create image view with the image resource per the received position argument value
        imageView = view.findViewById(R.id.imageView)
        val position =  arguments?.getInt("position", 0)
        when(position) {
            0 -> imageView.setImageResource(R.drawable.berlin)
            1 -> imageView.setImageResource(R.drawable.athens)
            2 -> imageView.setImageResource(R.drawable.tokyo)
            3 -> imageView.setImageResource(R.drawable.rome)
            4 -> imageView.setImageResource(R.drawable.amsterdam)
        }

        return view
    }
}