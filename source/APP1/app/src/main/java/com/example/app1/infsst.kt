package com.example.app1

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.app1.entities.Document
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [infsst.newInstance] factory method to
 * create an instance of this fragment.
 */
class infsst : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var active: Activity?=null
    private var exitFile: Button?=null
    private var selectFile: Button?=null
    private var notifyPath: TextView?=null
    private var nameFile: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_infsst, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var b: AlertDialog.Builder = AlertDialog.Builder(active)
        var v:View?=activity?.layoutInflater?.inflate(R.layout.fragment_infsst, null, false)
        Log.i("prueba","uno")
        inicializar(v)
        accionar()
        b.setView(v)

        return b.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("prueba","dos")
        if(context is Activity){
            Log.i("prueba","tres")
            active=context
        }
    }

    fun inicializar(view:View?){
        try {
            Log.i("prueba","4")
            this.exitFile=view?.findViewById(R.id.exitSST)
            this.notifyPath=view?.findViewById(R.id.messagePathSST)
            this.selectFile=view?.findViewById(R.id.selectSST)
            this.nameFile=view?.findViewById(R.id.nameFileSST)

        } catch (e: Exception) {

            e.printStackTrace()

        }
    }

    fun accionar(){
        try {

            this.selectFile?.setOnClickListener{view ->

                if(this.nameFile?.text.toString().trim().length>0){
                    val intent=Intent()
                    intent.type="application/pdf"
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.action=Intent.ACTION_GET_CONTENT
                    startActivityForResult(Intent.createChooser(intent,"Select"),1103)
                }else{
                    this.notifyPath?.text="Por favor digite el nombre del informe SST"
                }
            }
            this.exitFile?.setOnClickListener{view ->
                dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1103 && resultCode==Activity.RESULT_OK){
            try {
                val saveUri = data?.data
                var mDialog = ProgressDialog(context)
                mDialog.setMessage("Subiendo..")
                mDialog.show()

                val imageName: String = UUID.randomUUID().toString()
                var storeRef: StorageReference
                storeRef= FirebaseStorage.getInstance().getReference(this.nameFile?.text.toString()+".pdf")
                storeRef.putFile(saveUri!!).addOnSuccessListener {
                    mDialog.dismiss()
                    //Toast.makeText(context, "Imagen subida!", Toast.LENGTH_SHORT).show()
                    storeRef.downloadUrl.addOnSuccessListener { uri ->
                        notifyPath?.text="ARCHIVO CARGADO CON EXITO!!"
                        Log.i("path",uri.toString())
                        var db = FirebaseFirestore.getInstance()
                        val dbDocument = db.collection("SSTDocuments")
                        var doc= Document(this.nameFile?.text.toString()+".pdf",uri.toString(),"1","1")
                        var flag:Boolean=true

                        dbDocument.add(doc.toMap()).addOnSuccessListener { documentReference ->
                            flag=true

                        }.addOnFailureListener { e ->
                            flag=false
                        }
                        Log.i("insert",flag.toString())


                    }
                }.addOnProgressListener { taskSnapshot ->
                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    mDialog.setMessage("Subiendo: $progress%")
                }
            }catch (e:Exception){
                e.printStackTrace()

            }

        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment infsst.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            infsst().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}