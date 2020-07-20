package com.example.app1

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.graphics.green
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.app1.entities.Activity
import com.example.app1.entities.Project
import com.example.app1.models.ModelActivity
import com.example.app1.models.ModelProject
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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

    private var states = arrayOf("-Seleccione una opción-", "No finalizada","Finalizada" )
    private var spinnerp: Spinner? = null
    private var spinners: Spinner? = null
    private var na:EditText?=null
    private var rt:EditText?=null
    private var da:EditText?=null
    private var tv: TextView?=null
    private var msg: TextView?=null
    private var viz:Button?=null
    private var save:Button?=null
    private var idProject:String?=null
    private var newProject:FloatingActionButton?=null
    private var fm:FragmentManager?=null
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
        this.newProject=v.fab
        this.tv=v.description
        this.na=v.nameActivityText
        this.rt=v.responsableText
        this.da=v.descriptionText
        this.viz=v.visualize
        this.save=v.save
        this.msg=v.msg_user
        this.spinnerp =v.projects_works
        this.spinners=v.states_spinner
        this.fm=getFragmentManager()
        val projectsList = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, projects)
        projectsList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.spinnerp!!.adapter = projectsList

        val stateList = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, states)
        stateList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.spinners!!.adapter = stateList

    }


    fun getProjects():ArrayList<Project>{
        var projects: ArrayList<Project> = ArrayList()
        projects.add( Project(" - Seleccione un proyecto - ",""," "))
        //projects.add()
        try {
            var mp:ModelProject=ModelProject()
            projects=mp.listProjects()

        }catch (e: Exception){
                e.printStackTrace()

        }
        return projects

    }

    fun saveActivity() {
        Log.i("activityValue",this.spinners?.selectedItemPosition.toString())
        var numberIdProject:Int?=this.spinnerp?.selectedItemPosition?.toInt()
        var numberStateValue:Int?=this.spinners?.selectedItemPosition?.toInt()
        if(this.spinnerp!=null && numberIdProject!=null && numberIdProject>0){
            if (this.na != null && !this.na?.text.toString().isEmpty()) {
                if(this.rt!=null && !this.rt?.text.toString().isEmpty()){
                    if(this.da!=null && !this.da?.text.toString().isEmpty()) {
                        if(numberStateValue!=null && numberStateValue>0){
                            var act:Activity?=null
                            if(numberStateValue==1){
                                act=Activity(this.na?.text.toString(),this.rt?.text.toString(),this.da?.text.toString(), false, this.idProject.toString())
                            }else{
                                act=Activity(this.na?.text.toString(),this.rt?.text.toString(),this.da?.text.toString(), true, this.idProject.toString())
                            }
                            var ma:ModelActivity?=ModelActivity()
                            var r:Boolean?=ma?.insertActivity(act)

                            if(r==true){
                                this.na?.text=Editable.Factory.getInstance().newEditable("")
                                this.rt?.text=Editable.Factory.getInstance().newEditable("")
                                this.da?.text=Editable.Factory.getInstance().newEditable("")
                                this.spinnerp?.setSelection(0)
                                this.spinners?.setSelection(0)
                                this.viz?.isEnabled=false
                                this.save?.isEnabled=false
                                this.msg?.text="Actividad Creada con Éxito!!"

                            }else{
                                this.msg?.text="La actividad no pudo ser creada"
                            }
                        }else{
                            this.msg?.text = "Por favor seleccione el estado de la actividad"
                        }
                    }else{
                        this.msg?.text = "Por favor digite la descripción de la actividad"

                    }
                }else{
                    this.msg?.text = "Por favor digite el responsable de la actividad"

                }
            } else {
                this.msg?.text = "Por favor digite un nombre de la actividad"
            }
        }else{
            this.msg?.text = "Por favor seleccione un proyecto"
        }



    }

    fun itemSelection(position:Int,v: AdapterView<*>?){
        Log.i("positionProj",position.toString())
        if(position>0){
            val s=v?.getItemAtPosition(position) as Project
            Log.i("descriptionProj",s.toString())
            this.viz?.isEnabled=true
            this.save?.isEnabled=true
            this.tv?.text=s.getId().toString()+"-"+s.getDescription()
            this.idProject=s.getId().toString()
        }else{
            this.viz?.isEnabled=false
            this.save?.isEnabled=false
            this.tv?.text=""
        }

    }

    fun accionar(v:View){
        this.spinnerp?.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?,selectedItemView: View,position: Int,id: Long) {
                try {
                    itemSelection(position,parentView)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

        this.save?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                saveActivity()
                //var state=

            }
        })

        this.newProject?.setOnClickListener { view ->
            Snackbar.make(view, "Create New Project", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

                showDialog()
        }

    }
    fun showDialog(){
        val dialogFrag = FragmentProject()
        if(this.fm!=null){
            dialogFrag.setTargetFragment(this,1)
            fragmentManager?.let { dialogFrag.show(it,"Hola") }

            Log.i("Fragment Manager Open","Open")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val myData: String = data?.getStringExtra("listdata") ?: ""
        val projects: ArrayList<Project> =getProjects()
        val projectsList = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, projects)
        projectsList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.spinnerp!!.adapter = projectsList
        this.msg?.text=myData
        Log.i("FeedbackResult","result")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }
}