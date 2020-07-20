package com.example.app1.models

import com.example.app1.entities.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_activities_executing.view.*

class ModelUser {

    fun getUser():User{
        var  db= FirebaseAuth.getInstance()
        var u:User=User()
        u.uid=db.currentUser?.uid
        u.name=db.currentUser?.email.toString()
        
        return u

    }

}