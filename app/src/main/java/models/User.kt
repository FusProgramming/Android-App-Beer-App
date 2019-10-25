package models

class User {
    var uid: String? = null
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var address: String? = null
    var type: String? = null

    constructor() {}

    constructor(
        uid: String,
        name: String,
        email: String,
        password: String,
        address: String,
        type: String
    ) {
        this.uid = uid
        this.name = name
        this.email = email
        this.password = password
        this.address = address
        this.type = type
    }
}