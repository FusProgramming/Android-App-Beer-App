
package fragments

import adapter.AddBeerRecyclerViewAdapter
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidappfinalproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import models.AddSearchRecyclerViewAdapter
import models.Stores

class SearchBeerFragment : Fragment() {
    private var storeAdapter: AddSearchRecyclerViewAdapter? = null
    private var root: View? = null
    private var db: FirebaseFirestore? = null
    private var firestoreListener: ListenerRegistration? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = FirebaseFirestore.getInstance()
        root = inflater.inflate(R.layout.fragment_store, container, false)
        loadStoreList()
        firestoreListener = db!!.collection("stores")
            .addSnapshotListener(EventListener { documentSnapshots, e ->
                if (e != null) {
                    return@EventListener
                }
                val storeList = mutableListOf<Stores>()
                for (doc in documentSnapshots!!) {
                    val store = doc.toObject(Stores::class.java)
                    store.uid = doc.id
                    storeList.add(store)
                }
                storeAdapter = AddSearchRecyclerViewAdapter(storeList, context!!, db!!)
                val storeListRV = root!!
                    .findViewById<View>(R.id.store_list) as RecyclerView
                storeListRV.adapter = storeAdapter
            })
        return root
    }


    private fun loadStoreList() {
        db!!.collection("stores").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val storeList = mutableListOf<Stores>()
                for (doc in task.result!!) {
                    val store = doc.toObject<Stores>(Stores::class.java)
                    store.uid = doc.id
                    storeList.add(store)
                }
                storeAdapter = AddSearchRecyclerViewAdapter(storeList, context!!, db!!)
                val mLayoutManager = LinearLayoutManager(context!!)
                val storeListRV = root!!.findViewById<View>(R.id.store_list) as RecyclerView
                storeListRV.layoutManager = mLayoutManager
                storeListRV.itemAnimator = DefaultItemAnimator()
                storeListRV.adapter = storeAdapter
            }
        }
    }
}
