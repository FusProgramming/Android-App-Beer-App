package models

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.androidappfinalproject.LoginActivity
import com.example.androidappfinalproject.MapActivity
import com.example.androidappfinalproject.MapDataActivity
import com.example.androidappfinalproject.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class  AddSearchRecyclerViewAdapter(private val storeList: MutableList<Stores>,
                                   private val context: Context,
                                   private val db: FirebaseFirestore
) :
    RecyclerView.Adapter<AddSearchRecyclerViewAdapter.ViewHolder>() {
    private val arraylist: ArrayList<Stores>

    init {

        this.arraylist = ArrayList<Stores>()
        this.arraylist.addAll(storeList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = storeList[position]
        Log.d("FireBase", "1Information Added to FireStore")

        holder.storeName.text = store.storeName
        holder.storeAddress.text = store.storeAddress
        holder.beerName.text = store.beerName

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MapDataActivity::class.java)
            intent.putExtra("storeAddress", holder.storeAddress.text.toString())
            context.startActivity(intent)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("FireBase", "2Information Added to FireStore")
        val view = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.item_store, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        Log.d("FireBase", "3Information Added to FireStore")
        return storeList.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var storeName: TextView = view.findViewById(R.id.store_name_textview)
        internal var storeAddress: TextView = view.findViewById(R.id.store_address_type_textview)
        internal var beerName: TextView = view.findViewById((R.id.store_beer_name_textview))

    }


    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        storeList.clear()
        if (charText.length == 0) {
            storeList.addAll(arraylist)
        } else {
            for (wp in arraylist) {
                if (wp.beerName!!.toLowerCase(Locale.getDefault()).contains(charText)) {
                    storeList.add(wp)
                }
            }
        }
        notifyDataSetChanged()
    }
}

