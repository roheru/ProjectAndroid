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
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.entities.Meet
import com.example.app1.entities.ScheduleActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListActivitiesExecute.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListActivitiesExecute : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var rvv: RecyclerView?=null
    var active: Activity?=null
    private var nameAct:TextView?=null
    private var nameRes:TextView?=null
    private var nameProj:TextView?=null
    private var nameDesc:TextView?=null
    private var nameState:TextView?=null
    private var exitListAct:Button?=null
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
        //return inflater.inflate(R.layout.fragment_list_activities_execute, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var b: AlertDialog.Builder = AlertDialog.Builder(active)
        var v:View?=activity?.layoutInflater?.inflate(R.layout.fragment_list_activities_execute, null, false)

        inicializar(v)
        accionar(v!!)
        b.setView(v)
        this.rvv=v?.findViewById(R.id.recyclerViewListActExe)
        rvv?.layoutManager= LinearLayoutManager(active, LinearLayout.VERTICAL.toInt(),false)

        //var activities:ArrayList<ScheduleActivity>=ArrayList<ScheduleActivity>()
        val schedules: ArrayList<ScheduleActivity> = ArrayList()

        try {
            var db = FirebaseFirestore.getInstance()
            val activitiesCollection = db.collection("activities")
            activitiesCollection.get().addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d("DataValueID", document.id + " => " + document.data.get("name"))
                        //this.projects.set(this.projects.size,)
                        schedules.add(
                            ScheduleActivity(
                                document.data["name"].toString(),
                                document.data["responsable"].toString(),
                                document.data["description"].toString(),
                                document.data["idProject"].toString(),
                                document.data["state"].toString().toBoolean()
                            )
                        )
                    }
                    val adapterRecycle=MyItemRecyclerActExeAdapter(schedules)

                    //val adapterRecycle=MyItemRecyclerViewAdapter(data)
                    rvv?.adapter=adapterRecycle

                } else {
                    Log.d("DataValueError", "Error getting documents: ", task.exception)
                    task.exception?.printStackTrace()
                }
            })
        }catch(e:Exception){

        }

        return b.create()
    }

    fun inicializar(view:View?){
        this.nameAct=view?.findViewById(R.id.nameActivity)
        this.nameRes=view?.findViewById(R.id.nameResponsible)
        this.nameProj=view?.findViewById(R.id.nameProject)
        this.nameDesc=view?.findViewById(R.id.nameDescription)
        this.nameState=view?.findViewById(R.id.namestateActivity)
        this.exitListAct=view?.findViewById(R.id.exitListAct)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Activity){
            active=context
        }
    }

    fun accionar(view:View){
        this.exitListAct?.setOnClickListener{view ->
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
         * @return A new instance of fragment ListActivitiesExecute.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListActivitiesExecute().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}