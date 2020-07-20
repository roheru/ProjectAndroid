package com.example.app1.models

import android.util.Log
import com.example.app1.entities.Activity
import com.example.app1.entities.Meet
import com.example.app1.entities.Project
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

class ModelMeeting {

    constructor(){


    }

    fun insertMeeting(meet: Meet):Boolean{
        var flag=false
        try {
            var db = FirebaseFirestore.getInstance()
            val dbMeet = db.collection("meetings")
            val meet=meet.toMap()

            dbMeet.add(meet).addOnSuccessListener { documentReference ->
                flag=true

            }.addOnFailureListener { e ->
                flag=false
            }
        }catch (e: Exception){
            e.printStackTrace()

        }
        Log.i("flag",flag.toString())
        return true

    }

    fun listMeetings():ArrayList<Meet> {
        val meets: ArrayList<Meet> = ArrayList()
        try {

            var db = FirebaseFirestore.getInstance()
            val meetingsCollection = db.collection("meetings")
            meetingsCollection.get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            Log.d("DataValueID", document.id + " => " + document.data.get("name"))
                            //this.projects.set(this.projects.size,)
                            meets.add(
                                Meet(
                                    document.data["title"].toString(),
                                    document.data["description"].toString(),
                                    document.data["date"].toString(),
                                    document.data["hourb"].toString(),
                                    document.data["houre"].toString(),
                                    document.data["user"].toString()

                                    )
                            )
                        }

                    } else {
                        Log.d("DataValueError", "Error getting documents: ", task.exception)
                        task.exception?.printStackTrace()
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()

        }
        return meets
    }
}