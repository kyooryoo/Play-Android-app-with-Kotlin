package com.example.listfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class MyCityFragment : Fragment() {

    lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_my_city, container, false)

        imageView = view.findViewById(R.id.imageView)

        val position = arguments?.getInt("position", 0)

        when(position) {
            0 -> imageView.setImageResource(R.drawable.berlin)
            1 -> imageView.setImageResource(R.drawable.tokyo)
            2 -> imageView.setImageResource(R.drawable.newyork)
            3 -> imageView.setImageResource(R.drawable.london)
            4 -> imageView.setImageResource(R.drawable.shanghai)
        }

        // Inflate the layout for this fragment
        return view
    }

}