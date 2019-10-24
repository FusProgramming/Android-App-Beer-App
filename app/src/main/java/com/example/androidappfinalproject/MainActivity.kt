package com.example.androidappfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_nav_bar.*
import com.google.firebase.auth.FirebaseUser
import fragments.ProfileUserFragment
import fragments.SearchFragment
import kotlinx.android.synthetic.main.bottom_nav_bar_admin.*
import kotlinx.android.synthetic.main.bottom_nav_bar_signedin.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            setContentView(R.layout.activity_main)
            Toast.makeText(this, "Welcome ${user?.uid}", Toast.LENGTH_SHORT).show()
            val intentLogin = Intent(this, ProfileActivity::class.java)
            startActivity(intentLogin)

        } else {
            setContentView(R.layout.activity_main)
            Toast.makeText(this, "Welcome ${user?.uid}", Toast.LENGTH_SHORT).show()
        }
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



