package adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidappfinalproject.R
import com.google.firebase.firestore.FirebaseFirestore
import models.Beers

class AddBeerRecyclerViewAdapter(private val beerList: MutableList<Beers>,
                                 private val context: Context,
                                 private val db: FirebaseFirestore) :
    RecyclerView.Adapter<AddBeerRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beerList[position]
        Log.d("FireBase", "1Information Added to FireStore")

        holder.beerName.text = beer.beerName
        holder.beerType.text = beer.beerType

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("FireBase", "2Information Added to FireStore")
        val view = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.item_beer, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("FireBase", "3Information Added to FireStore")
        return beerList.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var beerName: TextView = view.findViewById(R.id.beer_name_textview)
        internal var beerType: TextView = view.findViewById(R.id.beer_type_textview)
    }

    fun removeAt(position: Int) {
        beerList.removeAt(position)
        notifyItemRemoved(position)
    }
}