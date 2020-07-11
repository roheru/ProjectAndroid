package com.example.app1.entities

class Activity {


    private var name: String? = null
    private var responsable: String? = null
    private var description:String?=null
    private var id:String?=null
    private var idProject:String?=null
    private var state:Boolean?=null

    constructor(name:String,responsable: String,description:String,id:String,state: Boolean){
        this.name=name
        this.responsable=responsable
        this.description=description
        this.id=id
        this.state=state
    }

    constructor(name:String,responsable: String,description:String,state: Boolean){
        this.name=name
        this.responsable=responsable
        this.description=description
        this.state=state
    }

    constructor(name:String,responsable: String,description:String,state: Boolean,idProject:String){
        this.name=name
        this.responsable=responsable
        this.description=description
        this.state=state
        this.idProject=idProject
    }

    fun setName(name: String) {
        this.name = id
    }

    fun getName(): String? {
        return this.name
    }

    fun setResponsable(responsable: String) {
        this.responsable = responsable
    }

    fun getResponsable(): String? {
        return this.responsable
    }


    fun setDescription(description: String) {
        this.responsable = responsable
    }

    fun getDescription(): String? {
        return this.description
    }

    fun setState(state: Boolean) {
        this.state = state
    }

    fun getState(): Boolean? {
        return this.state
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getId(): String? {
        return this.id
    }

    fun setIdProject(idProject: String) {
        this.idProject = idProject
    }

    fun getIdProject(): String? {
        return this.idProject
    }

    fun toMap():Map<String,Any>{
        val result=HashMap<String,Any>()
        result.put("description",this.description!!)
        result.put("idProject","test")
        result.put("name",this.name!!)
        result.put("responsable",this.responsable!!)
        result.put("state",this.state?.toString()!!)
        return result
    }
}