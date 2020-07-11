package com.example.app1.entities


class Project {



    private var name: String? = null
    private var description: String? = null
    private var id:Int?=null

    constructor(name:String,description:String,id:Int){
        this.name=name
        this.description=description
        this.id=id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int? {
        return this.id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName():String {
        return this.name.toString()
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getDescription():String {
        return this.description.toString()
    }

    override fun toString(): String {
        return this.name.toString()
    }
}