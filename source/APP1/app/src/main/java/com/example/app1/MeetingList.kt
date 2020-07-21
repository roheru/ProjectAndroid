package com.example.app1

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.entities.Meet
import com.example.app1.entities.User
import com.example.app1.models.ModelMeeting
import com.example.app1.models.ModelUser
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.fragment_item_meet.view.*
import kotlinx.android.synthetic.main.fragment_meeting_list.view.*
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MeetingList.newInstance] factory method to
 * create an instance of this fragment.
 */
class MeetingList : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    internal var exitM:FloatingActionButton?=null
    internal var saveM:FloatingActionButton?=null


    private var textDate:TextView?=null
    private var message:TextView?=null

    private var dateMessage:String?=null
    private var hourbtext:EditText?=null
    private var houretext:EditText?=null
    private var subjectM:EditText?=null
    private var descriptionM:EditText?=null

    private var buttonhoure:ImageButton?=null
    private var buttonhourb:ImageButton?=null
    private var rvv:RecyclerView?=null
    private var meets:ArrayList<Meet>?=null
    var active:Activity?=null

    fun setDateMessage(datem:String){
        this.dateMessage=datem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("prueba","entro")
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    @SuppressLint("WrongConstant")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var b: AlertDialog.Builder = AlertDialog.Builder(active)
        var v:View?=activity?.layoutInflater?.inflate(R.layout.fragment_meeting_list, null, false)

        inicializar(v)
        accionar()

        b.setView(v)
        this.rvv=v?.findViewById(R.id.recyclerViewList)

        rvv?.layoutManager=LinearLayoutManager(active, LinearLayout.VERTICAL.toInt(),false)


        var data:ArrayList<Meet>?=ArrayList<Meet>()

        var mm:ModelMeeting= ModelMeeting()

        //data=mm.listMeetings()

        val meets: ArrayList<Meet> = ArrayList()
        try {

            var db = FirebaseFirestore.getInstance()
            val meetingsCollection = db.collection("meetings")
            meetingsCollection.get().addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            Log.d("DataValueID", document.id + " => " + document.data.get("name"))
                            //this.projects.set(this.projects.size,)
                            meets.add(
                                Meet(
                                    document.data["title"].toString(),
                                    document.data["description"].toString(),
                                    document.data["date"].toString(),
                                    document.data["hourb"].toString(),
                                    document.data["houre"].toString(),
                                    document.data["user"].toString()

                                )
                            )
                        }
                        val adapterRecycle=MyItemRecyclerViewAdapter(meets)

                        //val adapterRecycle=MyItemRecyclerViewAdapter(data)
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



    fun getMessagesFromDatabase(){

    }



    fun inicializar(view:View?){
        this.exitM=view?.exitM
        this.saveM=view?.saveM
        this.textDate=view?.titleDay
        this.hourbtext=view?.hourbM
        this.houretext=view?.houreM
        this.buttonhourb=view?.buttonhourb
        this.buttonhoure=view?.buttonhoure
        this.subjectM=view?.asuntoM
        this.descriptionM=view?.descriptionM
        this.message=view?.message
        this.textDate?.text="Reunión del día "+this.dateMessage.toString()
    }

    fun accionar(){
        this.exitM?.setOnClickListener{view ->
            dismiss()
        }

        this.buttonhourb?.setOnClickListener { view->
            val mTimePicker: TimePickerDialog

            mTimePicker = TimePickerDialog(this?.context,
                OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    var sm:String=selectedMinute.toString()
                    var sh:String=selectedHour.toString()

                    if(selectedHour<10){
                        sh="0"+sh
                    }

                    if(selectedMinute<10){
                        sm="0"+sm
                    }

                    this.hourbtext?.setText(sh+":"+sm)
                    },
                12,
                12,
                true
            )
            mTimePicker.setTitle("Hola")
            mTimePicker.show()

        }

        this.buttonhoure?.setOnClickListener{view->
            val mTimePicker: TimePickerDialog

            mTimePicker = TimePickerDialog(this?.context,
                OnTimeSetListener { timePicker, selectedHour, selectedMinute ->

                    var sm:String=selectedMinute.toString()
                    var sh:String=selectedHour.toString()

                    if(selectedHour<10){
                        sh="0"+sh
                    }

                    if(selectedMinute<10){
                        sm="0"+sm
                    }

                    this.houretext?.setText(sh+":"+sm)
                },
                12,
                12,
                true
            ) //Yes 24 hour time

            mTimePicker.setTitle("Hola")
            mTimePicker.show()
        }
        this.saveM?.setOnClickListener{view ->
            if(insertMeet()){
                dismiss()
            }else{

            }

        }

    }

    fun insertMeet():Boolean{
        try {
            var mu:ModelUser= ModelUser()
            var u:User=mu.getUser()


            if(this.subjectM!=null && this.subjectM?.text!=null && this.subjectM?.text.toString().length>0){
                if(this.descriptionM!=null && this.descriptionM?.text!=null && this.descriptionM?.text.toString().length>0){
                    if(this.hourbtext!=null && this.hourbtext?.text!=null && this.hourbtext?.text.toString().length>0){
                        if(this.houretext!=null && this.houretext?.text!=null && this.houretext?.text.toString().length>0){

                            val hourbegin=this.hourbtext?.text.toString().split(":").toTypedArray()
                            val hourend=this.houretext?.text.toString().split(":").toTypedArray()

                            var hb=hourbegin[0].toInt()
                            var mb=hourbegin[1].toInt()
                            var he=hourend[0].toInt()
                            var me=hourend[1].toInt()

                            if(he>hb){
                                insertMeet(u)
                                return true
                            }else if(he==hb && mb>me){
                                insertMeet(u)
                                return true
                            }else{
                                this.message?.text="Rango de horas de no válido"
                            }

                        }else{
                            this.message?.text="Por favor verifique la hora final de la reunión"
                        }
                    }else{
                        this.message?.text="Por favor verifique la hora inicial de la reunión"
                    }
                }else{
                    this.message?.text="Por favor verifique la descripción de la reunión"
                }
            }else{
                this.message?.text="Por favor verifique el asunto de la reunión"
            }

        }catch (e:Exception){
            e.printStackTrace()
            this.message?.text="Error creación de reunión"
        }
        return false
    }

    fun insertMeet(u:User){
        var meet:Meet= Meet()
        var mm:ModelMeeting= ModelMeeting()
        meet.title=this.subjectM?.text.toString()
        meet.description=this.descriptionM?.text.toString()
        meet.date=this.dateMessage.toString()
        meet.hourb=this.hourbtext?.text.toString()
        meet.houre=this.houretext?.text.toString()
        meet.user=u.uid

        meet?.let { it1 -> mm.insertMeeting(it1) }
        val intent = Intent()
        intent.putExtra("listdata", "Reunión creada con exito!!")
        targetFragment!!.onActivityResult(targetRequestCode, 1, intent)
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
         * @return A new instance of fragment MeetingList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MeetingList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}