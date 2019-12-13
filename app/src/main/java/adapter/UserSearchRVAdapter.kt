package adapter

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
import models.Stores
import java.util.*
import kotlin.collections.ArrayList

class UserSearchRVAdapter(private val storeList: MutableList<Stores>,
                                    private val context: Context,
                                    private val db: FirebaseFirestore
) :
    RecyclerView.Adapter<UserSearchRVAdapter.ViewHolder>() {
    private val arraylist: ArrayList<Stores> = ArrayList()
    init {
        this.arraylist.addAll(storeList)
    }
//--------------------------------------------------------------------------------------------------
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = storeList[position]
        Log.d("FireBase", "Information Added to FireStore")
    //This info is to help search for the information in MapData to connect it to Google Maps.
    //It will transmit the store address to google maps
        holder.storeName.text = store.storeName
        holder.storeAddress.text = store.storeAddress
        holder.beerName.text = store.beerName
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MapDataActivity::class.java)
            intent.putExtra("storeAddress", holder.storeAddress.text.toString())
            context.startActivity(intent)
        }
    }

//--------------------------------------------------------------------------------------------------
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.item_store, parent, false)
        return ViewHolder(view)

    }

//--------------------------------------------------------------------------------------------------
    override fun getItemCount(): Int {
        return storeList.size
    }

//--------------------------------------------------------------------------------------------------
    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var storeName: TextView = view.findViewById(R.id.store_name_textview)
        internal var storeAddress: TextView = view.findViewById(R.id.store_address_type_textview)
        internal var beerName: TextView = view.findViewById((R.id.store_beer_name_textview))

    }

//--------------------------------------------------------------------------------------------------
    //This will filter the text for beerName so that the user can specifically look for beer names
    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        storeList.clear()
        if (charText.isEmpty()) {
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
