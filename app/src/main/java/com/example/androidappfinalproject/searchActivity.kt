package com.example.androidappfinalproject

import adapter.AddBeerRecyclerViewAdapter
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import fragments.AddBeerFragment
import fragments.SearchBeerFragment
import kotlinx.android.synthetic.main.activity_admin_add_beer.*
import kotlinx.android.synthetic.main.bottom_nav_bar.*
import kotlinx.android.synthetic.main.bottom_nav_bar_signedin.*
import models.Beers

class searchActivity : AppCompatActivity() {

    private var beerAdapter: AddBeerRecyclerViewAdapter? = null
    private var root: View? = null
    private var db: FirebaseFirestore? = null
    private var firestoreListener: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance()
        setContentView(R.layout.activity_search)
        replaceFragment(SearchBeerFragment())
        bottomNavViewBar.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.beer_nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.BeerProfileButton -> {
                replaceFragment(SearchBeerFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
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






