package user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidappfinalproject.R
import com.example.androidappfinalproject.searchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
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
}