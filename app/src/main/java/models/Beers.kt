package models

import java.util.HashMap

class Beers {
    var id: String? = null
    var beerName: String? = null
    var beerType: String? = null

    constructor() {}

    constructor(
        id: String,
        beerName: String,
        beerType: String
        ) {
        this.id = id
        this.beerName = beerName
        this.beerType = beerType

    }
    constructor(
        beerName: String,
        beerType: String
    ) {
        this.beerName = beerName
        this.beerType = beerType
    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("beer", beerName!!)
        result.put("type", beerType!!)

        return result
    }
}