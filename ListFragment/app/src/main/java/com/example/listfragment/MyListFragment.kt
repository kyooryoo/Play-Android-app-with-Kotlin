package com.example.listfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.ListFragment

class MyListFragment : ListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayAdapter =
            activity?.let {
                ArrayAdapter.createFromResource(
                    it,
                    R.array.cities,
                    android.R.layout.simple_list_item_1
                )
            }

        listAdapter = arrayAdapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)

        val city = l.getItemAtPosition(position) as String
        Toast.makeText(requireContext(), "You chose: $city", Toast.LENGTH_SHORT).show()

        val intent = Intent(activity, SecondActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }

}