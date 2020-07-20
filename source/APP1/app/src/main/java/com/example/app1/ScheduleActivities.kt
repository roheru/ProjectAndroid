package com.example.app1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_schedule_activities.*

import kotlinx.android.synthetic.main.fragment_schedule_activities.view.*
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleActivities.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleActivities : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var calendarview:CalendarView?=null
    private var labeDate:TextView?=null
    private var fm: FragmentManager?=null
    private var createMeeting:Button?=null
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
        val viewv=inflater.inflate(R.layout.fragment_schedule_activities, container, false)
        inicializar(viewv)
        accionar(viewv)
        return viewv
    }


    fun getCurrentDate():String{
        var calendar:Calendar
        var dateFormat:SimpleDateFormat=SimpleDateFormat("dd / MM / yyyy")
        calendar= Calendar.getInstance()

        var date:String=dateFormat.format(calendar.time)
        return date

    }

    fun inicializar(view:View){
        this.calendarview=view.calendarMeet
        this.labeDate=view.labelDate
        this.labeDate?.text=getCurrentDate()
        this.createMeeting=view.createMeeting
        this.fm=getFragmentManager()

    }
    fun accionar(view:View){
        this.calendarview?.setOnDateChangeListener(OnDateChangeListener { calendarView, i, i1, i2 ->
            var day:String=i2.toString()
            var month:String=(i1+1).toString()
            if(i2<10){
                day="0"+i2.toString()
            }
            if((i1+1)<10){
                month="0"+(i1+1).toString()
            }
            val msg = "$day / $month / $i"
        this.labelDate.text=msg
        })
        this.createMeeting?.setOnClickListener { view ->
            showDialog()
        }
    }

    fun showDialog(){
        val dialogFrag = MeetingList()
        var args:Bundle= Bundle()
        args.putString("date","yyy//dasdasd")
        dialogFrag.arguments=args
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
         * @return A new instance of fragment ScheduleActivities.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScheduleActivities().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}