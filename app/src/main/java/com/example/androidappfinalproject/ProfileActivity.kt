package com.example.androidappfinalproject

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import fragments.*
import kotlinx.android.synthetic.main.bottom_nav_bar_signedin.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottomNavViewBarSignedIn.onNavigationItemSelectedListener= nOnNavigationItemSelectedListener

    }


    private val nOnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener { i->
        when(i.itemId){
            R.id.navigation_user_search-> {
                /*
                replaceFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true

                 */
            }
            R.id.navigation_user_profile -> {
                replaceFragment(ProfileUserFragment())
                return@OnNavigationItemSelectedListener true
            }

        }

        false

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}