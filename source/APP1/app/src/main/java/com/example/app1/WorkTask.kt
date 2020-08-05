package com.example.app1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.app1.entities.Project
import com.example.app1.entities.Quad
import com.example.app1.models.ModelProject
import com.example.app1.models.ModelTask
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_work_task.*
import kotlinx.android.synthetic.main.fragment_work_task.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkTask.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkTask : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var spinnerp: Spinner? = null
    private var rvv:RecyclerView?=null
    private var saveTask:FloatingActionButton?=null

    private var project:Project?=null
    private var quadId:EditText?=null
    private var area:EditText?=null
    private var person:EditText?=null
    private var task:EditText?=null
    private var msg:TextView?=null
    //private var srl:SwipeRefreshLayout?=null


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
        var v:View=inflater.inflate(R.layout.fragment_work_task, container, false)
        initialize(v)
        accionar()
        return v
    }

    fun initialize(v:View){
        val projects: ArrayList<Project> =getProjects()
        this.spinnerp =v.projects_works_task
        val projectsList = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, projects)
        projectsList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        this.saveTask=v.saveTask

        this.quadId=v.quadgroup
        this.area=v.areagroup
        this.person=v.namegroup
        this.task=v.taskgroup
        this.msg=v.msg





        this.spinnerp!!.adapter = projectsList
        var p:Project?= this.spinnerp?.selectedItem as Project
        this.project=p


        this.rvv=v.findViewById<RecyclerView>(R.id.RecyclerViewQuad)
        rvv?.layoutManager= LinearLayoutManager(this.context, LinearLayout.VERTICAL.toInt(),false)


        /*if(p!=null && position!=null && position>0){

        }*/
        var mt:ModelTask= ModelTask()
        mt.getAllTask(this.rvv!!)

        refrescar()

    }

    fun refrescar(){

        /*this.srl?.setOnRefreshListener {
            var mt:ModelTask= ModelTask()
            var position:Int?= this.spinnerp?.selectedItemPosition
            var p:Project?= this.spinnerp?.selectedItem as Project
            this.project=p
            this.project?.let { mt.getAllTaskByProject(this.rvv!!, it) }
            this.rvv?.adapter!!.notifyDataSetChanged()
            this.srl!!.isRefreshing=true
        }

        swipeRefreshLayout?.setOnRefreshListener {
            var mt:ModelTask= ModelTask()
            var position:Int?= this.spinnerp?.selectedItemPosition
            var p:Project?= this.spinnerp?.selectedItem as Project
            this.project=p
            this.project?.let { mt.getAllTaskByProject(this.rvv!!, it) }
            this.rvv?.adapter!!.notifyDataSetChanged()
            this.srl!!.isRefreshing=true
        }
*/
    }

    fun accionar(){
        this.saveTask?.setOnClickListener {
            var position:Int?= this.spinnerp?.selectedItemPosition
            var p:Project?= this.spinnerp?.selectedItem as Project
            if(p!=null && position!=null && position>0){
                if(this.quadId!=null && this.quadId!!.text.length>0){
                    if(this.area!=null && this.area!!.text.length>0){
                        if(this.person!=null && this.person!!.text.length>0){
                            if(this.task!=null && this.task!!.text.length>0){
                                var q:Quad=Quad(this.quadId?.text.toString(),
                                    p.getId().toString(),
                                    this.area?.text.toString(),
                                    this.person?.text.toString(),
                                    this.task?.text.toString())

                                var mt:ModelTask=ModelTask()
                                mt.insertTask(q)
                                rvv?.adapter?.notifyItemInserted(0)
                                this.msg?.text="Tarea guardada con éxito"
                            }else{
                                this.msg?.text="Por favor digite la tarea asignada"
                            }
                        }else{
                            this.msg?.text="Por favor digite el responsable"
                        }
                    }else{
                        this.msg?.text="Por favor digite el área de trabajo"
                    }
                }else{
                    this.msg?.text="Por favor digite la cuadrilla"
                }
            }else{
                this.msg?.text="Por favor seleccione un proyecto"
            }
        }

        this.spinnerp!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                msg?.text="cambio: "+position
                var mt:ModelTask= ModelTask()
                var position:Int?= spinnerp?.selectedItemPosition
                var p:Project?= spinnerp?.selectedItem as Project
                project=p
                project?.let { mt.getAllTaskByProject(rvv!!, it) }
                //rvv?.adapter!!.notifyDataSetChanged()
                //refrescar()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })


    }

    fun getProjects():ArrayList<Project>{
        var projects: ArrayList<Project> = ArrayList()

        //projects.add()
        try {
            var mp: ModelProject = ModelProject()
            projects=mp.listProjects()

        }catch (e: Exception){
            e.printStackTrace()

        }


        return projects

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WorkTask.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WorkTask().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

