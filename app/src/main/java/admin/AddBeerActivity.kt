package admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidappfinalproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import fragments.AddBeerFragment
import kotlinx.android.synthetic.main.activity_admin_add_beer.*
import kotlinx.android.synthetic.main.bottom_nav_bar_admin.*
import models.Beers

class AddBeerActivity : AppCompatActivity(), View.OnClickListener {

    private var db: FirebaseFirestore? = null
    internal var id: String? = ""
//--------------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_beer)
        db = FirebaseFirestore.getInstance() //FireStore instance
        buttonAddBeer.setOnClickListener(this)
        bottomNavViewBarAdmin.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }

//--------------------------------------------------------------------------------------------------
    override fun onStart() {
        super.onStart()
        addBeerName.visibility = View.VISIBLE
        addBeerType.visibility = View.VISIBLE
        buttonAddBeer.visibility = View.VISIBLE
    }

//--------------------------------------------------------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.beer_nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

//--------------------------------------------------------------------------------------------------
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.BeerProfileButton -> {
                addBeerName.visibility = View.GONE
                addBeerType.visibility = View.GONE
                buttonAddBeer.visibility = View.GONE
                addBeerCompany.visibility = View.GONE
                addBeerPhoto.visibility = View.GONE
                replaceFragment(AddBeerFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

//--------------------------------------------------------------------------------------------------
    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.buttonAddBeer -> addBeer()
        }

    }

//--------------------------------------------------------------------------------------------------
    private fun addBeer() {
        val beerCompany = addBeerCompany.text.toString()
        val beerName = addBeerName.text.toString()
        val typeBeer = addBeerType.text.toString()

        if(beerCompany.isEmpty() || beerName.isEmpty() || typeBeer.isEmpty()) {
            Toast.makeText(this, "Please Fill Information", Toast.LENGTH_SHORT).show()
            Log.d("Beer", "Beer Items Empty")
            if(beerCompany.isEmpty()) { //Toast for beer Company
                Toast.makeText(this, "Beer Company Empty", Toast.LENGTH_SHORT).show()
                Log.d("Beer", "Company Name Empty")
            }
            if(beerName.isEmpty()) { // Toast for beer Name
                Toast.makeText(this, "Beer Name Empty", Toast.LENGTH_SHORT).show()
                Log.d("Beer", "Beer Name Empty")
            }
            if(typeBeer.isEmpty()) { //Toast for beer Company
                Toast.makeText(this, "Beer Type Empty", Toast.LENGTH_SHORT).show()
                Log.d("Beer", "Beer Type Empty")
            }
        }
        Log.d("FireBase", "DocumentSnapshot added with ID")
        saveUserToFirebase(id.toString(),beerCompany, beerName,typeBeer)
        Log.d("FireBase", "DocumentSnapshot added with ID")

    }

//--------------------------------------------------------------------------------------------------
    //Saves users to firebase database
    private fun saveUserToFirebase(id: String,beerCompany: String, beerName: String, beerType: String) {
        val beer = Beers(id,beerCompany,beerName,beerType).toMap()
        Log.d("FireBase", "DocumentSnapshot added with ID")
        db!!.collection("beers") //Adds to collection in firebase
            .add(beer)
            .addOnSuccessListener { documentReference ->
                Log.d("FireBase", "DocumentSnapshot written with ID: " + documentReference.id)
                Log.d("FireBase", "DocumentSnapshot added with ID: ${id}")
            }.addOnFailureListener {
                Log.d("FireBase", "DocumentSnapshot Failed with ID")
            }

    }

//--------------------------------------------------------------------------------------------------
    //Fragement replacer. Allows for fragments to replace activity.
    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

//--------------------------------------------------------------------------------------------------
    //Bottom Navigation Bar Code
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.admin_add_beer -> { //Send to Activity
                    val intent = Intent(this, AddBeerActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.admin_profile -> { //Send to Activity
                    val intent2 = Intent(this, ProfileAdminActivity::class.java)
                    startActivity(intent2)
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
}


