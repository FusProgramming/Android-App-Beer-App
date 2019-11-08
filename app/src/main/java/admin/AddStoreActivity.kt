package admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidappfinalproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.bottom_nav_bar_admin.*

class AddStoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_store)
        bottomNavViewBarAdmin.onNavigationItemSelectedListener= mOnNavigationItemSelectedListener
    }
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item2 ->
            when (item2.itemId) {
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
                else -> false
            }
        }


}