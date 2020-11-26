package com.example.app1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_documents.*
import kotlinx.android.synthetic.main.fragment_documents.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Documents.newInstance] factory method to
 * create an instance of this fragment.
 */
class Documents : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var uploadFilePlain:Button?=null
    private var uploadFileSST:Button?=null
    private var uploadFileObra:Button?=null
    private var listFilePlain:Button?=null
    private var listFileSST:Button?=null
    private var listFileConstruction:Button?=null
    private var docPlaneImage:ImageView?=null
    private var infoSSTImage:ImageView?=null
    private var infoConstImage:ImageView?=null
    private var listplanoImage:ImageView?=null
    private var listsstImage:ImageView?=null
    private var listobraImage:ImageView?=null
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
        val viewv=inflater.inflate(R.layout.fragment_documents, container, false)
        inicializar(viewv)
        accionar(viewv)
        return viewv
    }

    fun inicializar(view:View){
        //this.uploadFileSST=view.infoSST

        this.docPlaneImage=view.plainImage
        this.infoSSTImage=view.infoSSTImage
        this.infoConstImage=view.infoConsImage
        this.listplanoImage=view.listplanoImage
        this.listsstImage=view.listsstImage
        this.listobraImage=view.listobraImage
        this.fm=getFragmentManager()
    }
    fun accionar(view:View){




        this.listplanoImage?.setOnClickListener { view ->
            showDialogListPlain()
        }

        this.listsstImage?.setOnClickListener { view ->
            showDialogListSST()
        }
        this.listobraImage?.setOnClickListener { view ->
            showDialogListConstruction()
        }
        this.docPlaneImage?.setOnClickListener { view ->
            showDialogPlain()
        }

        this.infoSSTImage?.setOnClickListener { view ->
            showDialogSST()
        }
        this.infoConstImage?.setOnClickListener { view ->
            showDialogObra()
        }

    }

    fun showDialogPlain(){
        val dialogFrag = UploadPlain()
        Log.i("Fragment Manager Open","CLick upload pdf")
        if(this.fm!=null){
            dialogFrag.setTargetFragment(this,1)
            fragmentManager?.let { dialogFrag.show(it,"Hola") }

            Log.i("Fragment Manager Open","CLick upload pdf")
        }
    }


    fun showDialogSST(){
        val dialogFrag = infsst()
        Log.i("Fragment Manager Open","CLick upload pdf")
        if(this.fm!=null){
            dialogFrag.setTargetFragment(this,1)
            fragmentManager?.let { dialogFrag.show(it,"Hola") }

            Log.i("Fragment Manager Open","CLick upload pdf")
        }
    }

    fun showDialogObra(){
        val dialogFrag = infObra()
        Log.i("Fragment Manager Open","CLick upload pdf")
        if(this.fm!=null){
            dialogFrag.setTargetFragment(this,1)
            fragmentManager?.let { dialogFrag.show(it,"Hola") }

            Log.i("Fragment Manager Open","CLick upload pdf")
        }
    }

    fun showDialogListPlain(){
        val dialogFrag = listPlain()
        Log.i("Fragment Manager Open","CLick upload pdf")
        if(this.fm!=null){
            dialogFrag.setTargetFragment(this,1)
            fragmentManager?.let { dialogFrag.show(it,"Hola") }

            Log.i("Fragment Manager Open","CLick upload pdf")
        }

    }

    fun showDialogListSST(){
        val dialogFrag = list_sst()
        Log.i("Fragment Manager Open","CLick upload pdf")
        if(this.fm!=null){
            dialogFrag.setTargetFragment(this,1)
            fragmentManager?.let { dialogFrag.show(it,"Hola") }

            Log.i("Fragment Manager Open","CLick upload pdf")
        }

    }

    fun showDialogListConstruction(){
        val dialogFrag = list_construction()
        Log.i("Fragment Manager Open","CLick upload pdf")
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
         * @return A new instance of fragment Documents.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Documents().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}