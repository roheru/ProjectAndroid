package com.example.app1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.app1.entities.Material
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_materials.*
import kotlinx.android.synthetic.main.fragment_materials.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Materials.newInstance] factory method to
 * create an instance of this fragment.
 */
class Materials : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var saveMateriales: Button?=null
    private var vizMateriales: Button?=null
    private var nameMaterial:EditText?=null
    private var refMaterial:EditText?=null
    private var descMaterial:EditText?=null
    private var qMaterial:EditText?=null
    private var stateMaterial:EditText?=null
    private var messageMater:TextView?=null
    private var fm: FragmentManager?=null


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
        val viewv=inflater.inflate(R.layout.fragment_materials, container, false)
        inicializar(viewv)
        accionar(viewv)

        return viewv
    }
    fun inicializar(view:View){
        this.saveMateriales=view.saveMaterial
        this.vizMateriales=view.vizMaterial
        this.nameMaterial=view.nameMaterial
        this.refMaterial=view.reference
        this.descMaterial=view.descriptionm
        this.qMaterial=view.quantity
        this.stateMaterial=view.stateSol
        this.messageMater=view.message
        this.fm=getFragmentManager()
        

    }
    fun accionar(view:View){

        this.saveMateriales?.setOnClickListener { view ->
            saveMaterialAction()
        }



        this.vizMateriales?.setOnClickListener { view ->
            listMaterials()
        }

    }

    fun saveMaterialAction(){
        try {
            var db = FirebaseFirestore.getInstance()
            val dbMaterial = db.collection("materials")
            val mat=Material(this.nameMaterial?.text.toString(),this.refMaterial?.text.toString(),this.descMaterial?.text.toString(),this.qMaterial?.text.toString().toInt(),this.stateMaterial?.text.toString().toBoolean())
            var flag:Boolean=true
            dbMaterial.add(mat.toMap()).addOnSuccessListener { documentReference ->
                flag=true

            }.addOnFailureListener { e ->
                flag=false
            }
            if(flag){
                this.messageMater?.text="El material ha sido guardado con éxito."
                Log.i("InsertMat","Ok")
            }else{
                this.messageMater?.text="Error guardando la información"
                Log.i("InsertMat","Error")
            }
        }catch(e:Exception){
            e.printStackTrace()
        }

    }
    fun listMaterials(){
        val dialogFrag = ListMaterials()
        if(this.fm!=null){
            dialogFrag.setTargetFragment(this,1)
            fragmentManager?.let { dialogFrag.show(it,"Hola") }

            Log.i("Fragment Manager Open","CLick upload pdf")
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Materials.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Materials().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}