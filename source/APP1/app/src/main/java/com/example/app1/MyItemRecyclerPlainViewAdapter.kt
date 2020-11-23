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
import com.example.app1.entities.Document


class MyItemRecyclerPlainViewAdapter: RecyclerView.Adapter<MyItemRecyclerPlainViewAdapter.ViewHolder> {
    private var listElements: ArrayList<Document>?=null
    private var resource: Int?=null

    constructor(){

    }


    constructor(listElements: ArrayList<Document>){
        this.listElements=listElements


    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyItemRecyclerPlainViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_document, parent, false)
        val vh: MyItemRecyclerPlainViewAdapter.ViewHolder =ViewHolder(view)


        return vh
    }

    override fun getItemCount(): Int = this.listElements?.size!!

    override fun onBindViewHolder(
        holder: MyItemRecyclerPlainViewAdapter.ViewHolder,
        position: Int
    ) {
        this.listElements?.get(position)?.let { holder.bindItems(it) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val idView: TextView = view.findViewById(R.id.item_number)
        //val contentView: TextView = view.findViewById(R.id.content)



        fun bindItems(data:Document){
            val nameDocument:TextView = itemView.findViewById(R.id.nameDocument)
            val idProj:TextView = itemView.findViewById(R.id.projectIdDoc)
            val linkDoc:TextView = itemView.findViewById(R.id.linkDoc)
            val linkButton:Button= itemView.findViewById(R.id.buttonLinkDoc)
            nameDocument.text=data.getName()
            idProj.text=data.getidProject()
            linkDoc.text=data.getURL()
            linkButton?.setOnClickListener { view ->
                Log.i("Print Message",linkDoc.text.toString())
            }
            //accionar()

        }
/*
        fun accionar(){
            val linkButton:Button= itemView.findViewById(R.id.buttonLinkDoc)
            linkButton?.setOnClickListener { view ->
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));

                //ContextCompat.startActivity(browserIntent);
            }

        }
*/




    }

}