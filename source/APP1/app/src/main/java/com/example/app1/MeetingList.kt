package com.example.app1

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.entities.Meet
import kotlinx.android.synthetic.main.fragment_meeting_list.view.*


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
    internal var exitM:Button?=null
    internal var createM:Button?=null
    private var dateMessage:String?=null
    private var textDate:TextView?=null
    private var hourbtext:EditText?=null
    private var houretext:EditText?=null

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

        return super.onCreateView(inflater, container, savedInstanceState)
    }



    @SuppressLint("WrongConstant")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var b: AlertDialog.Builder = AlertDialog.Builder(active)
        var v:View?=activity?.layoutInflater?.inflate(R.layout.fragment_meeting_list, null, false)

        inicializar(v)
        accionar()

        b.setView(v)
        val rv:RecyclerView?=v?.findViewById(R.id.recyclerViewList)

        rv?.layoutManager=LinearLayoutManager(active, LinearLayout.VERTICAL.toInt(),false)


        val data=ArrayList<Meet>()
        data.add(Meet("Reunion 1:","Reunión sobre aplicación de perros","12","13"))
        data.add(Meet("Reunion 2:","Reunión sobre aplicación de gatos","10","15"))
        data.add(Meet("Reunion 3:","Reunión sobre aplicación de aves","1","13"))


        val adapterRecycle=MyItemRecyclerViewAdapter(data)
        rv?.adapter=adapterRecycle
        return b.create()
    }

    fun inicializar(view:View?){
        this.exitM=view?.exitM
        this.createM=view?.createM
        this.textDate=view?.titleDay
        this.hourbtext=view?.hourb
        this.houretext=view?.houre
        this.textDate?.text="Reunión del día "+this.dateMessage.toString()
    }

    fun accionar(){
        this.exitM?.setOnClickListener{view ->
            dismiss()
        }

        this.hourbtext?.setOnClickListener{view->
            val mTimePicker: TimePickerDialog

            mTimePicker = TimePickerDialog(this?.context,
                OnTimeSetListener { timePicker, selectedHour, selectedMinute -> this.hourbtext?.setText("$selectedHour:$selectedMinute") },
                12,
                12,
                true
            ) //Yes 24 hour time

            mTimePicker.setTitle("Hola")
            mTimePicker.show()
        }

        this.houretext?.setOnClickListener{view->
            val mTimePicker: TimePickerDialog

            mTimePicker = TimePickerDialog(this?.context,
                OnTimeSetListener { timePicker, selectedHour, selectedMinute -> this.houretext?.setText("$selectedHour:$selectedMinute") },
                12,
                12,
                true
            ) //Yes 24 hour time

            mTimePicker.setTitle("Hola")
            mTimePicker.show()
        }


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