package com.example.androidappfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import models.User

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        buttonRegisterAccount.setOnClickListener(this)
        buttonAdminAccount.setOnClickListener(this)
    }

    private fun adminAccountAccess() {
        val intentAdmin = Intent(this, RegisterAdminActivity::class.java)
        startActivity(intentAdmin)
    }

    private fun preformRegister() {
        val name = registerName.text.toString()
        val password = registerPassword.text.toString()
        val email = registerEmail.text.toString()
        val address = registerCity.text.toString()

        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please Enter Information", Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("RegisterActivity", "Name is: " + name)
        Log.d("RegisterActivity", "Password is: $password")
        Log.d("RegisterActivity", "Email is: " + email)
        Log.d("RegisterActivity", "City is: " + address)

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    return@addOnCompleteListener
                }
                saveUserToFirebaseDatabase()
                Log.d("Register", "Successful Register: ${it.result?.user?.uid}")
                Toast.makeText(this, "Welcome ${it.result?.user}", Toast.LENGTH_SHORT).show()

                val intentLogin = Intent(this, ProfileActivity::class.java)
                startActivity(intentLogin)
            }
            .addOnFailureListener {
                Log.d("Register", "Failed to create User ${it.message}")
                Toast.makeText(this, "Unsuccessful Login", Toast.LENGTH_SHORT).show()
            }

    }

    private fun saveUserToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val user = User(uid, registerName.text.toString())
        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("RegisterActivity", "Finally we saved the user to Firebase Database")
            }
            .addOnFailureListener {
                Log.d("RegisterActivity", "Failed to set value to database: ${it.message}")
            }
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.buttonRegisterAccount -> preformRegister()
            R.id.buttonAdminAccount -> adminAccountAccess()
        }

    }
}
