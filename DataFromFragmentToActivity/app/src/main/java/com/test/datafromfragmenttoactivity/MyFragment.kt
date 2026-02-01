package com.test.datafromfragmenttoactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class MyFragment : Fragment() {

    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var send: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_my, container, false)

        name = view.findViewById(R.id.editTextName)
        email = view.findViewById(R.id.editTextEmail)
        send = view.findViewById(R.id.buttonSend)

        send.setOnClickListener {
            val userName = name.text.toString()
            val userEmail = email.text.toString()

            (activity as MainActivity).takeData(userName, userEmail)

//            val mainActivity: MainActivity = activity as MainActivity
//            mainActivity.takeData(userName, userEmail)
        }

        // Inflate the layout for this fragment
        return view
    }
}