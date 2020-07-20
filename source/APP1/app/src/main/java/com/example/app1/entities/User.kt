package com.example.app1.entities

class User {
    var uid:String?=null
    var name:String?=null


    constructor(){}

    constructor(uid:String?,name:String?){
        this.uid=uid
        this.name=name
    }
}