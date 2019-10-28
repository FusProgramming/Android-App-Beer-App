package com.example.androidappfinalproject

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.bottom_nav_bar.*
import kotlinx.android.synthetic.main.bottom_nav_bar_signedin.*

class searchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        bottomNavViewBar.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
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






