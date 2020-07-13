package com.example.app1.models

import com.example.app1.entities.Project
import com.google.firebase.firestore.FirebaseFirestore
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
}