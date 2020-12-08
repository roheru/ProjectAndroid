package com.example.app1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.entities.Document
import com.example.app1.entities.Material

class MyItemRecyclerMatAdapter: RecyclerView.Adapter<MyItemRecyclerMatAdapter.ViewHolder>{

    private var listElements: ArrayList<Material>?=null
    private var resource: Int?=null

    constructor(listElements: ArrayList<Material>){
        this.listElements=listElements
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyItemRecyclerMatAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_list_mat, parent, false)
        val vh: MyItemRecyclerMatAdapter.ViewHolder =ViewHolder(view)


        return vh
    }

    override fun onBindViewHolder(
        holder: MyItemRecyclerMatAdapter.ViewHolder,
        position: Int
    ) {
        this.listElements?.get(position)?.let { holder.bindItems(it) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(data: Material){
            val nameMaterial:TextView=itemView.findViewById(R.id.nameMat)
            val refMaterial:TextView=itemView.findViewById(R.id.refMat)
            val descMaterial:TextView=itemView.findViewById(R.id.descMat)
            val cantMaterial:TextView=itemView.findViewById(R.id.cantMat)
            val estadoMat:TextView=itemView.findViewById(R.id.estadoMat)



        }
    }

    override fun getItemCount(): Int = this.listElements?.size!!

}