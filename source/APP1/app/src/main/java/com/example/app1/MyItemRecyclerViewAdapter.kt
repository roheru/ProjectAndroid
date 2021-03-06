package com.example.app1

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.app1.dummy.DummyContent.DummyItem
import com.example.app1.entities.Meet
import kotlinx.android.synthetic.main.fragment_item_meet.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private var listElements: ArrayList<Meet>?=null
    private var resource: Int?=null

    constructor(){

    }

    constructor(listElements: ArrayList<Meet>){
        this.listElements=listElements


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_meet, parent, false)
        val vh:ViewHolder=ViewHolder(view)


        return vh
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        this.listElements?.get(position)?.let { holder.bindItems(it) }
    }



    override fun getItemCount(): Int = this.listElements?.size!!




    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val idView: TextView = view.findViewById(R.id.item_number)
        //val contentView: TextView = view.findViewById(R.id.content)



        fun bindItems(data:Meet){
            val title: TextView = itemView.findViewById(R.id.titleCard)
            val description: TextView = itemView.findViewById(R.id.descriptionCard)
            val date:TextView= itemView.findViewById(R.id.dateCard)
            val hourb:TextView= itemView.findViewById(R.id.hourbCard)
            val houre:TextView= itemView.findViewById(R.id.houreCard)
            title.text=data.title
            description.text=data.description
            date.text=data.date
            hourb.text=data.hourb
            houre.text=data.houre

        }






    }
}