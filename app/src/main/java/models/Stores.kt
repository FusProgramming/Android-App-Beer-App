package models

import com.google.firebase.firestore.GeoPoint
import java.util.HashMap

class Stores {
    var uid: String? = null
    var storeName: String? = null
    var storeAddress: String? = null
    var beerName: String? = null
    var geoPoint: GeoPoint? = null


    constructor() {}

    constructor(
        id: String,
        storeName: String,
        storeAddress: String,
        beerName: String,
        geoPoint: GeoPoint
    ) {
        this.uid = id
        this.storeName = storeName
        this.storeAddress = storeAddress
        this.beerName = beerName
        this.geoPoint = geoPoint

    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("storeName",storeName!!)
        result.put("storeAddress", storeAddress!!)
        result.put("beerName", beerName!!)
        result.put("geoPoint", geoPoint!!)

        return result
    }
}