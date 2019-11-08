package models

import java.util.HashMap

class Beers {
    var uid: String = ""
    var beerCompany: String = ""
    var beerName: String = ""
    var beerType: String = ""

    constructor() {}

    constructor(
        id: String,
        beerCompany: String,
        beerName: String,
        beerType: String
        ) {
        this.uid = id
        this.beerCompany = beerCompany
        this.beerName = beerName
        this.beerType = beerType

    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("beerCompany", beerCompany)
        result.put("beerName", beerName)
        result.put("beerType", beerType)

        return result
    }
}