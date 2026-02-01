package com.test.datafromactivitytofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class BmiFragment : Fragment() {

    lateinit var result: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_bmi, container, false)

        val weight = arguments?.getInt("weight")!!.toInt()
        val height = arguments?.getInt("height")!!.toInt()
        val bmi: Double = ((weight * 10000) / (height * height)).toDouble()

        result = view.findViewById(R.id.textViewResult)
        "Your BMI is $bmi".also { result.text = it }

        // Inflate the layout for this fragment
        return view
    }
}