package com.example.app1.models

import android.content.Intent
import androidx.core.app.ActivityCompat
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception

class ModelUploadFile {


    private val  DOC=1000

    constructor(){

    }

    fun uploadFileToFirebase(){
        try {
            /*
            var storeRef: StorageReference

            storeRef=FirebaseStorage.getInstance().getReference("documentsUpload")
            val intent=Intent()
            intent.type="pdf"
            intent.action=Intent.ACTION_GET_CONTENT

            ActivityCompat.startActivityForResult(null,Intent.createChooser(intent,"Select"),DOC)
            */


        }catch (e: Exception){


            e.printStackTrace()
        }
    }

}