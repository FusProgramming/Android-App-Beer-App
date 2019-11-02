package models

import java.util.HashMap

class Stores {
    var uid: String? = null
    var storeName: String? = null
    var storeAddress: String? = null
    var beerName: String? = null


    constructor() {}

    constructor(
        id: String,
        storeName: String,
        storeAddress: String,
        beerName: String
    ) {
        this.uid = id
        this.storeName = storeName
        this.storeAddress = storeAddress
        this.beerName = beerName

    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("storeName",storeName!!)
        result.put("storeAddress", storeAddress!!)
        result.put("beerName", beerName!!)

        return result
    }
}