package com.example.androidappfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*
import models.User
import user.ProfileActivity

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

//--------------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        buttonRegisterAccount.setOnClickListener(this)
    }

//--------------------------------------------------------------------------------------------------
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
                saveUserToFirebase()
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

//--------------------------------------------------------------------------------------------------
    private fun saveUserToFirebase() {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val db = FirebaseFirestore.getInstance()
        val user = User(
            uid,
            registerName.text.toString(),
            registerEmail.text.toString(),
            registerPassword.text.toString(),
            registerCity.text.toString(),
            "User"
        )
        db.collection("users").document(uid).set(user)
            .addOnSuccessListener {
                Log.d("Regi", "DocumentSnapshot added with ID")
            }
            .addOnFailureListener { e ->
                Log.w("Regi", "Error adding document", e)
            }

    }
//--------------------------------------------------------------------------------------------------
    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.buttonRegisterAccount -> preformRegister()
        }

    }
}
