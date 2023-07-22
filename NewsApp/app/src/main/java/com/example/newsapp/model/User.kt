package com.example.newsapp.model

class User {
    var name: String? = null
    var lastname: String? = null
    var email: String? = null
    var uid: String? = null
    var type: String? = null

    constructor(){}
    constructor(name: String?, lastname: String?, email: String?, uid: String?, type: String?) {
        this.name = name
        this.lastname = lastname
        this.email = email
        this.uid = uid
        this.type = type
    }
}