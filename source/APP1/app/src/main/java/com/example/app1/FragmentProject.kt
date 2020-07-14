package com.example.app1

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.app1.entities.Project
import com.example.app1.models.ModelProject
import kotlinx.android.synthetic.main.fragment_project.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentProject.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentProject : DialogFragment() {
    // TODO: Rename and change types oMainActivityf parameters
    private var param1: String? = null
    private var param2: String? = null
    var active:Activity?=null
    var barraSuperior:LinearLayout?=null
    var btnSalir:Button?=null
    var btnGuardar:Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }



    fun accionar(){

        btnSalir?.setOnClickListener {
            val intent = Intent()
            intent.putExtra("listdata", "")
            targetFragment!!.onActivityResult(targetRequestCode, 1, intent)
               dismiss()
        }


        btnGuardar?.setOnClickListener {
            var nameTextS:String?=this.nameProject.text.toString()
            var descriptionTextS:String?=this.descriptionProject.text.toString()

            if(nameTextS!=null && nameTextS.length>0){

                if(descriptionTextS!=null && descriptionTextS.length>0){
                    var mp:ModelProject  = ModelProject()
                    var project:Project?= Project(nameTextS,descriptionTextS,"")
                    project?.let { it1 -> mp.insertProject(it1) }
                    val intent = Intent()
                    intent.putExtra("listdata", "Proyecto creado con exito!!")
                    targetFragment!!.onActivityResult(targetRequestCode, 1, intent)

                    dismiss()
                    // val ftran: android.app.FragmentTransaction? = activity!!.fragmentManager?.beginTransaction()
                    //ftran?.detach(this.parentFragment!!)?.attach(this)?.commit()
                }else{
                    this.msg.text="Por favor digite una descripción"

                }
            }else{
                this.msg.text="Por favor digite un nombre de Proyecto"

            }






        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var b: AlertDialog.Builder =AlertDialog.Builder(active)
        var v:View?=activity?.layoutInflater?.inflate(R.layout.fragment_project, null, false)
        b.setView(v)


        barraSuperior=v?.findViewById(R.id.barraSuperior)
        btnSalir=v?.findViewById(R.id.botonExit)
        btnGuardar=v?.findViewById(R.id.botonSave)

        accionar()
        return b.create()
    }

  /*  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

    }*/
    override fun onResume() {
        super.onResume()
        val params = dialog?.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window!!.attributes = params as android.view.WindowManager.LayoutParams
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
         * @return A new instance of fragment FragmentProject.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentProject().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}