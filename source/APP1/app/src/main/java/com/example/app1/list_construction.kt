package com.example.app1

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.entities.Document
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [list_construction.newInstance] factory method to
 * create an instance of this fragment.
 */
class list_construction : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var rvv: RecyclerView?=null
    private var docs:ArrayList<Document>?=null
    private var exitButton: FloatingActionButton?=null
    var active: Activity?=null

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
        //return inflater.inflate(R.layout.fragment_list_construction, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var b: AlertDialog.Builder = AlertDialog.Builder(active)
        var v:View?=activity?.layoutInflater?.inflate(R.layout.fragment_list_construction, null, false)

        inicializar(v)
        accionar()
        b.setView(v)
        this.rvv=v?.findViewById(R.id.recyclerViewListConstruction)

        rvv?.layoutManager= LinearLayoutManager(active, LinearLayout.VERTICAL.toInt(),false)

        val docs: ArrayList<Document> = ArrayList()

        try {
            var db = FirebaseFirestore.getInstance()
            val documentsConstructionCollection = db.collection("obraDocuments")
            documentsConstructionCollection.get().addOnCompleteListener(OnCompleteListener <QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d("DataValueID", document.id + " => " + document.data.get("name"))
                        //this.projects.set(this.projects.size,)
                        docs.add(
                            Document(
                                document.data["name"].toString(),
                                document.data["url"].toString(),
                                document.data["idProject"].toString(),
                                document.data["id"].toString()
                            )
                        )
                    }
                    val adapterRecycle=MyItemRecyclerConstructionViewAdapter(docs)


                    rvv?.adapter=adapterRecycle

                } else {
                    Log.d("DataValueError", "Error getting documents: ", task.exception)
                    task.exception?.printStackTrace()
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()

        }
        return b.create()
    }


    fun inicializar(view:View?){
        this.exitButton=view?.findViewById(R.id.exitButton)
    }

    fun accionar(){
        try {
            this.exitButton?.setOnClickListener{view ->
                dismiss()
            }
        }catch (e:Exception){

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Activity){
            active=context
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment list_construction.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            list_construction().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}