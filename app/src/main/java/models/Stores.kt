package models

import com.google.firebase.firestore.GeoPoint
import java.util.HashMap

class Stores {
    var uid: String? = null
    var storeName: String? = null
    var storeAddress: String? = null
    var beerName: String? = null
    var addressLongitude: String? = null
    var addressLatitude: String? = null


    constructor() {}

    constructor(
        id: String,
        storeName: String,
        storeAddress: String,
        beerName: String,
        longitude: String,
        latitude: String
    ) {
        this.uid = id
        this.storeName = storeName
        this.storeAddress = storeAddress
        this.beerName = beerName
        this.addressLongitude = longitude
        this.addressLatitude = latitude

    }

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("storeName",storeName!!)
        result.put("storeAddress", storeAddress!!)
        result.put("beerName", beerName!!)
        result.put("storeAddressLat", addressLatitude!!)
        result.put("storeAddressLong", addressLongitude!!)
        return result
    }
}