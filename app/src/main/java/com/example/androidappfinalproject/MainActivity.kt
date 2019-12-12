package com.example.androidappfinalproject

import `interface`.StoreState
import actions.Actions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_admin_add_store.*
import kotlinx.android.synthetic.main.bottom_nav_bar.*
import kotlinx.android.synthetic.main.bottom_nav_bar_signedin.*
import models.Store
import state.AddStore
import state.StoreList
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private val predefinedStores: MutableList<Store> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showStoreList()

        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions,0)
        
        bottomNavViewBar.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }

    //--------------------------------------------------------------------------------------------------
    var currentState by Delegates.observable<StoreState>(
        StoreList(
            emptyList()
        ), { _, old, new ->
            renderViewState(new, old)
        })


    private fun renderViewState(newState: StoreState, oldState: StoreState) {
        when (newState) {
            is StoreList -> showStoreList()
            is AddStore -> showAddStoreView()
        }
        when (oldState) {
            is StoreList -> hideStoreList()
            is AddStore -> hideAddStoreView()
        }
    }

    private fun showStoreList() {
        storeList.visibility = View.VISIBLE
        add_store_button.setOnClickListener {
            currentState = currentState.consumeAction(Actions.AddStoreClicked())
        }
    }


    private fun showAddStoreView() {
        add_store_container.visibility = View.VISIBLE
    }


    private fun hideStoreList() {
        storeList.visibility = View.GONE
        hide_store_button.setOnClickListener {
            val selectedStore = predefinedStores
            currentState =
                currentState.consumeAction(Actions.PredefinedStoreSelected(selectedStore))
        }
    }

    private fun hideAddStoreView() {
        add_store_container.visibility = View.GONE
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { i ->
            when (i.itemId) {
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



