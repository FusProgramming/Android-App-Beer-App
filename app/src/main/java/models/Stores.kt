package models

import java.util.HashMap

class Stores {
    var uid: String? = null
    var storeName: String? = null
    var storeAddress: String? = null


    constructor() {}

    constructor(
        id: String,
        storeName: String,
        storeAddress: String
    ) {
        this.uid = id
        this.storeName = storeName
        this.storeAddress = storeAddress

    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("storeName",storeName!!)
        result.put("storeAddress", storeAddress!!)


        return result
    }
}