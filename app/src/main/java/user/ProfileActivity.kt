package user

import `interface`.StoreState
import actions.Actions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.androidappfinalproject.MainActivity
import com.example.androidappfinalproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.bottom_nav_bar_signedin.*
import models.Store
import models.StoreType
import kotlin.properties.Delegates

class ProfileActivity : AppCompatActivity() {

    private val predefinedStores: MutableList<Store> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        buildPredefinedStores()
        showStoreList(emptyList())
        bottomNavViewBarSignedIn.onNavigationItemSelectedListener =
            nOnNavigationItemSelectedListener

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sign_out_nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.signOutProfileButton -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val nOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { i ->
            when (i.itemId) {
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

    var currentState by Delegates.observable<StoreState>(
        StoreList(
            emptyList()
        ), { _, old, new ->
            renderViewState(new, old)
        })



    private fun buildPredefinedStores() {
        predefinedStores.add(Store("GrilledChicken", StoreType.GRINDER))

    }

    private fun renderViewState(newState: StoreState, oldState: StoreState) {
        when (newState) {
            is StoreList -> showStoreList(newState.store)
            is AddStore -> showAddSandwichView(predefinedStores)
        }
        when (oldState) {
            is StoreList -> hideStoreList()
            is AddStore -> hideAddStoreView()
        }
    }

    private fun showStoreList(sandwiches: List<Store>) {
        storeList.visibility = View.VISIBLE
        favorite_sandwiches_listview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sandwiches)
        add_sandwich_button.setOnClickListener {
            currentState = currentState.consumeAction(Actions.AddStoreClicked())
        }
    }


    private fun showAddSandwichView(predefinedSandwiches: List<Store>) {
        add_sandwich_container.visibility = View.VISIBLE
        predefined_sandwiches_listview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, predefinedSandwiches)
    }


    private fun hideStoreList() {
        storeList.visibility = View.GONE
        hide_sandwich_button.setOnClickListener {
            val selectedStore = predefinedStores
            currentState = currentState.consumeAction(Actions.PredefinedStoreSelected(selectedStore))
        }
    }

    private fun hideAddStoreView() {
        add_sandwich_container.visibility = View.GONE
    }
}