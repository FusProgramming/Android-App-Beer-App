package com.example.androidappfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.bottom_nav_bar.*
import kotlinx.android.synthetic.main.bottom_nav_bar_signedin.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions,0)

        val uid = FirebaseAuth.getInstance().uid ?: ""
        val db = FirebaseFirestore.getInstance()
/*
        val documentRef = db.collection("users").document(uid)
        documentRef.get()
            .addOnSuccessListener { document ->

                if (document != null) {
                    val value = document.getString("type")
                    if(value == "User") {
                        val intentLogin = Intent(this, ProfileActivity::class.java)
                        startActivity(intentLogin)
                    } else if(value == "Admin") {
                        val intentLogin = Intent(this, ProfileAdminActivity::class.java)
                        startActivity(intentLogin)
                    } else {

                    }
                }
            }

 */
        bottomNavViewBar.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { i ->
            when (i.itemId) {
                R.id.navigation_notifications -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    val intent2 = Intent(this, searchActivity::class.java)
                    startActivity(intent2)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_home -> {
                    val intent3 = Intent(this, MainActivity::class.java)
                    startActivity(intent3)
                    return@OnNavigationItemSelectedListener true
                }

                else -> false
            }
        }
}



