package user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.androidappfinalproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import fragments.UserSearchBeerFragment
import kotlinx.android.synthetic.main.bottom_nav_bar_signedin.*


class ProfileSearchActivity : AppCompatActivity() {
    private var db: FirebaseFirestore? = null

//--------------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        db = FirebaseFirestore.getInstance()
        setContentView(R.layout.activity_user_search)
        replaceFragment(UserSearchBeerFragment())
        bottomNavViewBarSignedIn.onNavigationItemSelectedListener = nOnNavigationItemSelectedListener
    }

//--------------------------------------------------------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_bar, menu)
        val searchItem = menu.findItem(R.id.searchBar)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String):Boolean {
                return false
            }
            override fun onQueryTextChange(newText:String):Boolean {
                searchData()
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

//--------------------------------------------------------------------------------------------------
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

//--------------------------------------------------------------------------------------------------
    private val nOnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener { i->
        when(i.itemId){
            R.id.navigation_user_search -> {
                val intent2 = Intent(this, ProfileSearchActivity::class.java)
                startActivity(intent2)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_user_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
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
