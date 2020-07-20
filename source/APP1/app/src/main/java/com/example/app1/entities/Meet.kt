package com.example.app1.entities

import java.util.*

class Meet{
    internal var title:String?=""
    internal var description:String?=""
    internal var date:String?=""
    internal var hourb:String?=""
    internal var houre:String?=""
    internal var user:String?=""

    constructor(){

    }
    constructor(title: String, description: String, date:String,hourb: String?, houre: String?,user:String?) {
        this.title = title
        this.description = description
        this.date=date
        this.hourb = hourb
        this.houre = houre
        this.user=user
    }

    fun toMap():Map<String,Any>{
        val result=HashMap<String,Any>()
        result.put("title",this.title!!)
        result.put("description",this.description!!)
        result.put("date",this.date!!)
        result.put("hourb",this.hourb!!)
        result.put("houre",this.houre!!)
        result.put("user",this.user!!)
        return result
    }


}
/*
 class Meet (var ){
    internal var title:String=""
        get() = field
        set(value) {
            field = value
        }
     internal var description:String=""
         get() = field
         set(value) {
             field = value
         }
     private var dateBegin:Date?=null
         get() = field
         set(value) {
             field = value
         }
     private var dateEnd:Date?=null
         get() = field
         set(value) {
             field = value
         }

     constructor(){
    }






}*/