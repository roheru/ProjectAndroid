package com.example.app1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
    private var saveMaterial: Button?=null
    private var nameMaterial:EditText?=null
    private var refMaterial:EditText?=null
    private var descMaterial:EditText?=null
    private var qMaterial:EditText?=null
    private var stateMaterial:EditText?=null


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
        return inflater.inflate(R.layout.fragment_materials, container, false)
    }
    fun inicializar(view:View){
        this.saveMaterial=view.saveMaterial
        this.nameMaterial=view.nameMaterial
        this.refMaterial=view.reference
        this.descMaterial=view.descriptionm
        this.qMaterial=view.quantity
        this.stateMaterial=view.stateSol

    }
    fun accionar(view:View){

        this.saveMaterial?.setOnClickListener { view ->

        }
    }

    fun saveMaterial(){


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