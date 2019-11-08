package com.example.androidappfinalproject

import adapter.AddBeerRecyclerViewAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import fragments.SearchBeerFragment
import kotlinx.android.synthetic.main.bottom_nav_bar.*
import models.Beers


class searchActivity : AppCompatActivity() {

    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance()
        setContentView(R.layout.activity_search)
        replaceFragment(SearchBeerFragment())

        bottomNavViewBar.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_bar, menu)
        val searchItem = menu.findItem(R.id.searchBar)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String):Boolean {
                Log.d("Regi", "DocumentSnapshot added with ID")
                return false
            }
            override fun onQueryTextChange(newText:String):Boolean {

                Log.d("Regi", "DocumentSnapshot added with ID")
                searchData()
                return false

            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.BeerProfileButton -> {
                replaceFragment(SearchBeerFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun searchData() {
        db!!.collection("beers").whereEqualTo("beerName", true)
            .get()
            .addOnSuccessListener { documents ->
                for (document  in documents) {
                    Log.d("beer", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("beer", "Error getting documents: ", exception)
            }
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






