package com.example.app1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import com.example.app1.entities.Project
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_login2.view.*
import kotlinx.android.synthetic.main.fragment_activities_executing.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ActivitiesExecuting.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActivitiesExecuting : Fragment(), AdapterView.OnItemSelectedListener  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var states = arrayOf("-Seleccione una opción-", "No finalizada","Finalizada" )
    var spinnerp: Spinner? = null
    var spinners: Spinner? = null
    var tv: TextView?=null
    var viz:Button?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewv=inflater.inflate(R.layout.fragment_activities_executing, container, false)
        initializeComponents(viewv)
        accionar(viewv)
        return viewv
    }
    fun initializeComponents(v:View){

        val projects: ArrayList<Project> =getProjects()
        var  db= FirebaseAuth.getInstance()
        v?.welcome?.text="Buen Día "+db.currentUser?.email.toString()
        tv=v.description
        viz=v.visualize
        spinnerp =v.projects_works
        spinners=v.states_spinner
        val projectsList = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, projects)
        projectsList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerp!!.adapter = projectsList

        val stateList = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, states)
        stateList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinners!!.adapter = stateList

    }


    fun getProjects():ArrayList<Project>{
        val projects: ArrayList<Project> = ArrayList()
        projects.add( Project("-Seleccione un proyecto",""," "))
        //projects.add()
        try {

            //example()
            var db = FirebaseFirestore.getInstance()
            //

            val workPlansProject = db.collection("workPlansProject")

            //val user=db.firestoreSettings.

            workPlansProject.get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            Log.d("DataValueID", document.id + " => " + document.data.get("name"))
                            //this.projects.set(this.projects.size,)
                            projects.add( Project(document.data.get("name").toString(),document.data.get("description").toString()," "))
                        }
                    } else {
                        Log.d("DataValueError", "Error getting documents: ", task.exception)
                        task.exception?.printStackTrace()
                    }
                })
        }catch (e: Exception){
                e.printStackTrace()

        }
        return projects

    }


    fun accionar(v:View){
        this.spinnerp?.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                try {

                    if(position>0){
                        val s=parentView?.getItemAtPosition(position) as Project
                        Log.i("descriptionProj",s.toString())
                        viz?.isEnabled=true
                        tv?.text=s.getId().toString()+"-"+s.getDescription()
                    }else{
                        viz?.isEnabled=false
                        tv?.text=""
                    }

                }catch (e:Exception){
                    e.printStackTrace()

                }


            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
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
         * @return A new instance of fragment ActivitiesExecuting.
         */
        // TODO: Rename and change types and number of parameters

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }
}