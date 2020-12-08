package com.example.app1.entities

class Material {

    private var name: String? = null
    private var reference: String? = null
    private var description:String?=null
    private var cantidad:Int?=null
    private var estado:Boolean?=null


    constructor(name:String,reference: String,description:String,cantidad:Int,estado:Boolean){
        this.name=name
        this.reference=reference
        this.description=description
        this.cantidad=cantidad
        this.estado=estado

    }
    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String? {
        return this.name
    }


    fun setReference(reference: String) {
        this.reference = reference
    }

    fun getReference(): String? {
        return this.reference
    }


    fun setDescription(description: String) {
        this.description = description
    }

    fun getDescription(): String? {
        return this.description
    }

    fun setCantidad(cantidad:Int){
        this.cantidad=cantidad
    }

    fun getCantidad():Int?{
        return this.cantidad
    }

    fun setEstado(estado:Boolean){
        this.estado=estado
    }

    fun getEstado():Boolean?{
        return this.estado
    }

    fun toMap():Map<String,Any>{
        val result=HashMap<String,Any>()
        result.put("name",this.name!!)
        result.put("referencia",this.reference!!)

        result.put("descripcion",this.description!!)
        result.put("cantidad",this.cantidad!!)
        result.put("estado",this.estado!!)
        return result
    }

}