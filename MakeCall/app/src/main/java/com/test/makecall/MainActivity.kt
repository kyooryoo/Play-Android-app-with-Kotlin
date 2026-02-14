package com.test.makecall

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    lateinit var phone: EditText
    lateinit var call: Button

    var userNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        call = findViewById(R.id.buttonCall)
        phone = findViewById(R.id.editTextPhone)

        call.setOnClickListener {
            userNumber = phone.text.toString()
            if (userNumber.isNotEmpty()) {
                startCall(userNumber)
            } else {
                Toast.makeText(
                    this,
                    "Please enter a valid phone number",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun startCall(userNumber: String) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                100)
        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = "tel:$userNumber".toUri()
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = "tel:$userNumber".toUri()
            startActivity(intent)

            Toast.makeText(this, "Permission Granted for $deviceId", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permission Denied for $deviceId", Toast.LENGTH_SHORT).show()
        }
    }
}