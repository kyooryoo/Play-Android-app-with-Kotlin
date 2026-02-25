package com.test.firebaseregister

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.test.firebaseregister.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {

    lateinit var addUserBinding: ActivityAddUserBinding
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("MyUsers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addUserBinding = ActivityAddUserBinding.inflate(layoutInflater)
        val view = addUserBinding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title = "Add User"

        addUserBinding.buttonAddUser.setOnClickListener {
            addUserToDatabase()
        }
    }

    fun addUserToDatabase() {
        val name: String = addUserBinding.editTextName.text.toString()
        val age: Int = addUserBinding.editTextAge.text.toString().toInt()
        val email: String = addUserBinding.editTextEmail.text.toString()
        val id: String = myReference.push().key.toString()

        val user = Users(name,age,email, id)
        myReference.child(id).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext,
                    "The new user has been added to the database",
                    Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(applicationContext,
                    task.exception.toString(),
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}