package com.example.app1.models

import android.util.Log
import com.example.app1.entities.Project
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

class ModelProject {


    constructor(){


    }

    fun insertProject(project:Project){

        try {
            var db = FirebaseFirestore.getInstance()
            val dbProject = db.collection("workPlansProject")
            val proj=project.toMap()

            dbProject.add(proj).addOnSuccessListener { documentReference ->


            }.addOnFailureListener { e ->
                e.printStackTrace()

            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }


    fun listProjects():ArrayList<Project>{
        val projects: ArrayList<Project> = ArrayList()
        try {
            projects.add( Project(" - Seleccione un proyecto - ",""," "))
            var db = FirebaseFirestore.getInstance()
            val workPlansProject = db.collection("workPlansProject")
            workPlansProject.get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            Log.d("DataValueID", document.id + " => " + document.data.get("name"))
                            //this.projects.set(this.projects.size,)
                            projects.add( Project(document.data.get("name").toString(),document.data.get("description").toString(),document.id))
                        }

                    } else {
                        Log.d("DataValueError", "Error getting documents: ", task.exception)
                        task.exception?.printStackTrace()
                    }
                })
        }catch (e:Exception){
            e.printStackTrace()

        }
        return projects

    }
}