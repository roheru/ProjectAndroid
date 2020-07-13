package com.example.app1.models

import android.util.Log
import com.example.app1.entities.Activity
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class ModelActivity {




    constructor(){

    }

    fun insertActivity(activity:Activity):Boolean{
        var flag=false
        try {
            var db = FirebaseFirestore.getInstance()
            val dbActivity = db.collection("activities")
            val activ=activity.toMap()

            dbActivity.add(activ).addOnSuccessListener { documentReference ->
                flag=true

            }.addOnFailureListener { e ->
                flag=false
            }
        }catch (e:Exception){
            e.printStackTrace()

        }
        Log.i("flag",flag.toString())
        return true

    }



}