
package fragments

import `interface`.ListClick
import adapter.AddBeerRecyclerViewAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import callback.SwipeToDeleteCallback
import com.example.androidappfinalproject.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.android.synthetic.main.fragment_beer.*
import kotlinx.android.synthetic.main.fragment_beer.view.*
import models.Beers

class AddBeerFragment : Fragment(), ListClick {

    val beerList = mutableListOf<Beers>()
    lateinit var beerAdapter: AddBeerRecyclerViewAdapter
    private var root: View? = null
    private var db: FirebaseFirestore? = null
    private var firestoreListener: ListenerRegistration? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = FirebaseFirestore.getInstance()
        root = inflater.inflate(R.layout.fragment_beer, container, false)

        loadBeerList()

        firestoreListener = db!!.collection("beers")
            .addSnapshotListener(EventListener { documentSnapshots, e ->
                if (e != null) {
                    return@EventListener
                }

                val beerList = mutableListOf<Beers>()
                for (doc in documentSnapshots!!) {
                    val beer = doc.toObject(Beers::class.java)
                    beer.uid = doc.id
                    beerList.add(beer)
                }
                beerAdapter = AddBeerRecyclerViewAdapter(beerList, context!!, db!!)
                val beerListRV = root!!
                    .findViewById<View>(R.id.beer_list) as RecyclerView
                beerListRV.adapter = beerAdapter
            })
        return root
    }

    private fun loadBeerList() {
        db!!.collection("beers").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val beerList = mutableListOf<Beers>()
                for (doc in task.result!!) {
                    val beer = doc.toObject<Beers>(Beers::class.java)
                    beer.uid = doc.id
                    beerList.add(beer)
                }

                beerAdapter = AddBeerRecyclerViewAdapter(beerList, context!!, db!!)
                val mLayoutManager = LinearLayoutManager(context!!)
                val beerListRV = root!!.findViewById<View>(R.id.beer_list) as RecyclerView
                beerListRV.layoutManager = mLayoutManager
                beerListRV.itemAnimator = DefaultItemAnimator()
                beerListRV.adapter = beerAdapter

                val swipeHandler = object : SwipeToDeleteCallback(context!!) {
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                        deleteRow(viewHolder.adapterPosition)
                    }
                }
                val itemTouchHelper = ItemTouchHelper(swipeHandler)
                itemTouchHelper.attachToRecyclerView(root?.beer_list!!)
            }
        }
    }

    override fun deleteRow(position: Int) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        db!!.collection("beers").document(uid).delete()
            .addOnCompleteListener {
                beerAdapter.removeAt(position)
                Toast.makeText(context, "Selection Has Been Deleted", Toast.LENGTH_SHORT).show()
            }
    }

}
