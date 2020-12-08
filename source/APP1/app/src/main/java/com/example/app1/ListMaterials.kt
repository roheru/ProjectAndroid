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
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.entities.Material
import com.example.app1.entities.ScheduleActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.fragment_list_materials.*
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListMaterials.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListMaterials : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var active: Activity?=null
    private var rvv: RecyclerView?=null
    private var exitListMaterial: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun inicializar(view:View?){
        this.exitListMaterial=view?.findViewById(R.id.exitListMat)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_materials, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var b: AlertDialog.Builder = AlertDialog.Builder(active)
        var v: View? = activity?.layoutInflater?.inflate(
            R.layout.fragment_list_materials,
            null,
            false
        )
        inicializar(v)
        accionar(v!!)
        b.setView(v)
        this.rvv=v?.findViewById(R.id.recyclerViewListMat)
        val materials: ArrayList<Material> = ArrayList()

        try {
            var db = FirebaseFirestore.getInstance()
            val materialsCollection = db.collection("materials")
            materialsCollection.get().addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d("DataValueID", document.id + " => " + document.data.get("name"))
                        //this.projects.set(this.projects.size,)
                        materials.add(
                            Material(
                                document.data["name"].toString(),
                                document.data["referencia"].toString(),
                                document.data["description"].toString(),
                                document.data["cantidad"].toString().toInt(),
                                document.data["state"].toString().toBoolean()
                            )
                        )
                    }
                    val adapterRecycle=MyItemRecyclerMatAdapter(materials)

                    //val adapterRecycle=MyItemRecyclerViewAdapter(data)
                    rvv?.adapter=adapterRecycle

                } else {
                    Log.d("DataValueError", "Error getting documents: ", task.exception)
                    task.exception?.printStackTrace()
                }
            })
        }catch (e:Exception){

        }

        return b.create()
    }


        override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Activity){
            active=context
        }
    }

    fun accionar(view:View){
        this.exitListMaterial?.setOnClickListener{view ->
            dismiss()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListMaterials.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListMaterials().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}