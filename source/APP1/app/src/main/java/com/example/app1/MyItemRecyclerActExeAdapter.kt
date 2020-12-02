package com.example.app1

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.entities.Activity
import com.example.app1.entities.Document
import com.example.app1.entities.ScheduleActivity

class MyItemRecyclerActExeAdapter: RecyclerView.Adapter<MyItemRecyclerActExeAdapter.ViewHolder> {
    private var listElements: ArrayList<ScheduleActivity>?=null
    private var resource: Int?=null


    constructor(listElements: ArrayList<ScheduleActivity>){
        this.listElements=listElements
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyItemRecyclerActExeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_list_act_ex, parent, false)
        val vh: MyItemRecyclerActExeAdapter.ViewHolder =ViewHolder(view)


        return vh
    }

    override fun getItemCount(): Int = this.listElements?.size!!

    override fun onBindViewHolder(
        holder: MyItemRecyclerActExeAdapter.ViewHolder,
        position: Int
    ) {
        this.listElements?.get(position)?.let { holder.bindItems(it) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val idView: TextView = view.findViewById(R.id.item_number)
        //val contentView: TextView = view.findViewById(R.id.content)



        fun bindItems(data:ScheduleActivity){
            val nameRes: TextView=itemView.findViewById(R.id.nameResponsible)
            val nameAct: TextView=itemView.findViewById(R.id.nameActivity)
            val nameProj: TextView=itemView?.findViewById(R.id.nameProject)
            val nameDesc: TextView=itemView?.findViewById(R.id.nameDescription)
            val nameState: TextView=itemView?.findViewById(R.id.namestateActivity)
            nameRes.text=data.getResponsable()
            nameAct.text=data.getName()
            nameProj.text=data.getIdProject()
            nameDesc.text=data.getDescription()
            nameState.text=data.getState().toString()

        /*
            idProj.text=data.getidProject()
            linkDoc.text=data.getURL()
            linkButton?.setOnClickListener { view ->
                Log.i("Print Message",linkDoc.text.toString())

                accionar(view,data.getURL().toString())
            }
*/

        }

        fun accionar(view: View,link:String){
            val linkButton:Button= itemView.findViewById(R.id.buttonLinkDoc)
                var browserIntent:Intent?=null

            browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link));
                view.context.startActivity(browserIntent)


        }





    }
}