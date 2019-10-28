package com.example.androidappfinalproject


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.androidappfinalproject.AddStoreActivity
import com.example.androidappfinalproject.ProfileAdminActivity
import com.example.androidappfinalproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fragments.AddBeerFragment
import fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_admin_add_beer.*
import kotlinx.android.synthetic.main.bottom_nav_bar_admin.*
import models.Beers

class AddBeerActivity : AppCompatActivity(), View.OnClickListener {

    private var db: FirebaseFirestore? = null
    internal var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_beer)

        db = FirebaseFirestore.getInstance()
        buttonAddBeer.setOnClickListener(this)
        bottomNavViewBarAdmin.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener

    }

    override fun onStart() {
        super.onStart()
        addBeerName.visibility = View.VISIBLE
        addBeerType.visibility = View.VISIBLE
        buttonAddBeer.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.beer_nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.BeerProfileButton -> {
                addBeerName.visibility = View.GONE
                addBeerType.visibility = View.GONE
                buttonAddBeer.visibility = View.GONE
                addBeerCompany.visibility = View.GONE
                replaceFragment(AddBeerFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.buttonAddBeer -> addBeer()
        }

    }


    private fun addBeer() {
        val beerCompany = addBeerCompany.text.toString()
        val beerName = addBeerName.text.toString()
        val typeBeer = addBeerType.text.toString()
        Log.d("FireBase", "DocumentSnapshot added with ID")
        saveUserToFirebase(id.toString(),beerCompany, beerName,typeBeer)
        Log.d("FireBase", "DocumentSnapshot added with ID")

    }



    private fun saveUserToFirebase(id: String,beerCompany: String, beerName: String, beerType: String) {
        val beer = Beers(id,beerCompany,beerName,beerType).toMap()
        Log.d("FireBase", "DocumentSnapshot added with ID")
        db!!.collection("beers")
            .add(beer)
            .addOnSuccessListener { documentReference ->
                Log.d("FireBase", "DocumentSnapshot written with ID: " + documentReference.id)
                Log.d("FireBase", "DocumentSnapshot added with ID: ${id}")
            }.addOnFailureListener {
                Log.d("FireBase", "DocumentSnapshot Failed with ID")
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
                R.id.admin_add_beer -> {
                    val intent = Intent(this, AddBeerActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.admin_profile -> {
                    val intent2 = Intent(this, ProfileAdminActivity::class.java)
                    startActivity(intent2)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.admin_add_Store -> {
                    val intent3 = Intent(this, AddStoreActivity::class.java)
                    startActivity(intent3)
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
}


