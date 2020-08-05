package com.example.app1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.app1.entities.Quad


class MyTaskRecyclerViewAdapter: RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder>{

    private var listElements: ArrayList<Quad>?=null
    private var resource: Int?=null

    constructor(listElements: ArrayList<Quad>){
        this.listElements=listElements


    }

    fun setData(q:ArrayList<Quad>){
        this.listElements?.clear()
        this.listElements?.addAll(q)
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_quad_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.listElements?.get(position)?.let { holder.bindItems(it) }
        //notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listElements?.size!!

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val idView: TextView = view.findViewById(R.id.item_number)
        //val contentView: TextView = view.findViewById(R.id.content)


        fun bindItems(data: Quad) {
            val quadid: TextView = itemView.findViewById(R.id.quadidCard)
            val area: TextView = itemView.findViewById(R.id.areaCard)
            val personal:TextView= itemView.findViewById(R.id.personalCard)
            val tarea:TextView= itemView.findViewById(R.id.taskCard)

            quadid.text=data.quadId
            area.text=data.area
            personal.text=data.person
            tarea.text=data.task

        }
    }


}


