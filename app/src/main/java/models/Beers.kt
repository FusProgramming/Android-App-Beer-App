package models

import java.util.HashMap

class Beers {
    var uid: String? = null
    var beerName: String? = null
    var beerType: String? = null

    constructor() {}

    constructor(
        id: String,
        beerName: String,
        beerType: String
        ) {
        this.uid = id
        this.beerName = beerName
        this.beerType = beerType

    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("beerName", beerName!!)
        result.put("beerType", beerType!!)

        return result
    }
}