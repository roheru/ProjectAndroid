package com.example.app1.models

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.MyItemRecyclerViewAdapter
import com.example.app1.MyTaskRecyclerViewAdapter
import com.example.app1.entities.Meet
import com.example.app1.entities.Project
import com.example.app1.entities.Quad
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

class ModelTask {

    constructor(){

    }

    fun insertTask(quad: Quad){

        try {
            var db = FirebaseFirestore.getInstance()
            val dbTask = db.collection("tasks")
            val q=quad.toMap()

            dbTask.add(q).addOnSuccessListener { documentReference ->


            }.addOnFailureListener { e ->
                e.printStackTrace()

            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }


    fun getAllTask(rvv: RecyclerView?){
        try {
            var tasks:ArrayList<Quad>?= ArrayList()

            var db = FirebaseFirestore.getInstance()
            val tasksCollection = db.collection("tasks").orderBy("quadId")
            tasksCollection.get().addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d("DataValueID", document.id + " => " + document.data.get("name"))
                        //this.projects.set(this.projects.size,)
                        if (tasks != null) {
                            tasks.add(
                                Quad(
                                    document.data["quadId"].toString(),
                                                    document.data["idProyect"].toString(),
                                                    document.data["area"].toString(),
                                                    document.data["person"].toString(),
                                                    document.data["task"].toString()


                                )
                            )
                            val adapterRecycle= MyTaskRecyclerViewAdapter(tasks)

                            //val adapterRecycle=MyItemRecyclerViewAdapter(data)
                            rvv?.adapter=adapterRecycle
                            rvv?.adapter?.notifyDataSetChanged()
                        }
                    }


                } else {
                    Log.d("DataValueError", "Error getting documents: ", task.exception)
                    task.exception?.printStackTrace()
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()

        }

    }

    fun getAllTaskByProject(rvv: RecyclerView?,p:Project){
        try {
            var tasks:ArrayList<Quad>?= ArrayList()

            var db = FirebaseFirestore.getInstance()
            var x=p.getId()
            x="2"
            val tasksCollection = db.collection("tasks").whereEqualTo("idProyect",x).orderBy("quadId")


            tasksCollection.get().addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d("DataValueID", document.id + " => " + document.data.get("name"))
                        //this.projects.set(this.projects.size,)
                        if (tasks != null) {
                            tasks.add(
                                Quad(
                                    document.data["quadId"].toString(),
                                    document.data["idProyect"].toString(),
                                    document.data["area"].toString(),
                                    document.data["person"].toString(),
                                    document.data["task"].toString()


                                )
                            )

                            val adapterRecycle= MyTaskRecyclerViewAdapter(tasks)
                            adapterRecycle.setData(tasks)
                            //val adapterRecycle=MyItemRecyclerViewAdapter(data)
                            rvv?.adapter=adapterRecycle
                            rvv?.adapter?.notifyDataSetChanged()
                        }
                    }


                } else {
                    Log.d("DataValueError", "Error getting documents: ", task.exception)
                    task.exception?.printStackTrace()
                }
            })
            rvv?.adapter?.notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()

        }

    }

}