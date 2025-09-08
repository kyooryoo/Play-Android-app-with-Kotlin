package com.example.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import kotlin.toString

class DialogFragment : DialogFragment() {

    lateinit var name : EditText
    lateinit var index : EditText
    lateinit var cancel : Button
    lateinit var ok : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_dialog, container, false)

        name = view.findViewById(R.id.editTextName)
        index = view.findViewById(R.id.editTextIndex)
        cancel = view.findViewById(R.id.buttonCancel)
        ok = view.findViewById(R.id.buttonOk)

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        cancel.setOnClickListener {
            dialog!!.dismiss()
        }

        ok.setOnClickListener {
            val cityName : String = name.text.toString()
            val cityIndex: Int? = try {
                val input = index.text.toString()
                if (input.isNotBlank()) input.toInt() else null
            } catch (e: NumberFormatException) {
                null // Return null if parsing fails
            }

            val index = getCityData(cityName,cityIndex)
            if (index == null) {
                android.app.AlertDialog.Builder(context)
                    .setTitle("Error")
                    .setMessage("Cannot find the city.")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            } else {
                val intent = Intent(activity, SecondActivity::class.java)
                intent.putExtra("position", index)
                startActivity(intent)
            }

            dialog!!.dismiss()
        }


        // Inflate the layout for this fragment
        return view
    }

    fun getCityData(cityName : String?, cityIndex : Int?): Int? {

        // Retrieve the cities array from resources
        val citiesArray = resources.getStringArray(R.array.cities)

        // Check if the cityName matches any city in the array
        cityName?.let { name ->
            val indexByName = citiesArray.indexOfFirst { it.equals(name, ignoreCase = true) }
            if (indexByName != -1) {
                // Check if the provided cityIndex matches the found index
                if (cityIndex != null && cityIndex == indexByName) {
                    return indexByName
                } else if (cityIndex == null) {
                    return indexByName
                }
            }
        }

        // Check if the cityIndex is valid and matches the city at that index
        cityIndex?.let { index ->
            if (index in citiesArray.indices) {
                val cityAtIndex = citiesArray[index]
                if (cityName == "" || cityAtIndex.equals(cityName, ignoreCase = true)) {
                    return index
                }
            }
        }

        return null
    }
}