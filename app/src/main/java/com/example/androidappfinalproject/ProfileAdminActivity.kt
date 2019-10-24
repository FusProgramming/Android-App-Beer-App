package com.example.androidappfinalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragments.AddBeerFragment
import fragments.AddStoreFragment
import fragments.ProfileFragment
import kotlinx.android.synthetic.main.bottom_nav_bar_admin.*

class ProfileAdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_profile)
        bottomNavViewBarAdmin.onNavigationItemSelectedListener= mOnNavigationItemSelectedListener
    }

    private val mOnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.admin_profile -> {
                replaceFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.admin_add_beer -> {
                replaceFragment(AddBeerFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.admin_add_Store -> {
                replaceFragment(AddStoreFragment())
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

