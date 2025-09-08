package com.example.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment


class FirstFragment : ListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create an array adapter and feed it to the out-of-the-box list adapter of this List Fragment class instance
        val arrayAdapter = activity?.let {
            ArrayAdapter.createFromResource(it, R.array.cities, android.R.layout.simple_list_item_1) }
        listAdapter = arrayAdapter

        // Open the second activity when list item is clicked and pass position argument to the second activity
        listView.setOnItemClickListener { adapterView, view, position, l ->
            val intent = Intent(activity, SecondActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

    }
}