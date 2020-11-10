package com.example.app1.entities

class Document {
    private var name: String? = null
    private var url: String? = null
    private var idProject:String?=null
    private var id:String?=null


    constructor(name:String,url: String,idProject:String,id:String){
        this.name=name
        this.url=url
        this.idProject=idProject
        this.id=id
    }


    fun setName(name: String) {
        this.name = id
    }

    fun getName(): String? {
        return this.name
    }

    fun setURL(url: String) {
        this.url = url
    }

    fun getURL(): String? {
        return this.url
    }

    fun setidProject(idProject: String) {
        this.idProject = idProject
    }

    fun getidProject(): String? {
        return this.idProject
    }

    fun setid(id: String) {
        this.id = id
    }

    fun getid(): String? {
        return this.id
    }

    fun toMap():Map<String,Any>{
        val result=HashMap<String,Any>()
        result.put("name",this.name!!)
        result.put("idProject",this.idProject!!)
        result.put("url",this.url!!)
        result.put("id",this.id!!)
        return result
    }


}