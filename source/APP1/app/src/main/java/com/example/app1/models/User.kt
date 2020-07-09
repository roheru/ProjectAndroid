package com.example.app1.models

class User {
    var id:String?=null
    var name:String?=null


    constructor(){}

    constructor(id:String?,name:String?){
        this.id=id
        this.name=name
    }
}