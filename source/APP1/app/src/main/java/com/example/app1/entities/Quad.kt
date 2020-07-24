package com.example.app1.entities

class Quad {
    internal var quadId:String?=null
        get() = field
        set(value) {
            field = value
        }
    internal var idProyect:String?=null
        get() = field
        set(value) {
            field = value
        }
    internal var area:String = ""
        get() = field
        set(value) {
            field = value
        }
    internal var person:String = ""
        get() = field
        set(value) {
            field = value
        }
    internal var task:String = ""
        get() = field
        set(value) {
            field = value
        }

    constructor(){

    }

    constructor(quadId:String,idProyect:String,area:String,person:String,task:String){
        this.quadId=quadId
        this.idProyect=idProyect
        this.area=area
        this.person=person
        this.task=task
    }

    fun toMap():Map<String,Any>{
        val result=HashMap<String,Any>()
        result.put("quadId",this.quadId!!)
        result.put("idProyect",this.idProyect!!)
        result.put("area",this.area!!)
        result.put("person",this.person!!)
        result.put("task",this.task!!)
        return result
    }


}