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
class MyItemRecyclerViewAdapter(
    //private val values: List<DummyItem>
    private val listElements: ArrayList<Meet>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_meet, parent, false)
        val vh:ViewHolder=ViewHolder(view)


        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listElements[position]
        holder.bindItems(item)
    }

    override fun getItemCount(): Int = listElements.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val idView: TextView = view.findViewById(R.id.item_number)
        //val contentView: TextView = view.findViewById(R.id.content)



        fun bindItems(data:Meet){
            val title: TextView = itemView.findViewById(R.id.titleCard)
            val description: TextView = itemView.findViewById(R.id.descriptionCard)

            title.text=data.title
            description.text=data.title


        }




    }
}