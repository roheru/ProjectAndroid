package com.example.app1

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.app1.entities.Document
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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
    private var saveGantt:FloatingActionButton?=null
    private var downloadGantt:FloatingActionButton?=null
    private var createMeeting:Button?=null
    private var notifyPath:TextView?=null
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

    fun inicializar(view: View){
        this.calendarview=view.calendarMeet
        this.labeDate=view.labelDate
        this.labeDate?.text=getCurrentDate()
        this.createMeeting=view.createMeeting
        this.saveGantt=view.saveExcel
        this.downloadGantt=view.ganttExcel
        this.notifyPath=view.notifyMsg
        this.fm=getFragmentManager()

    }
    fun accionar(view: View){
        this.calendarview?.setOnDateChangeListener(OnDateChangeListener { calendarView, i, i1, i2 ->
            var day: String = i2.toString()
            var month: String = (i1 + 1).toString()
            if (i2 < 10) {
                day = "0" + i2.toString()
            }
            if ((i1 + 1) < 10) {
                month = "0" + (i1 + 1).toString()
            }
            val msg = "$day / $month / $i"
            this.labelDate.text = msg
        })
        this.createMeeting?.setOnClickListener { view ->
            showDialog()
        }

        this.saveGantt?.setOnClickListener { view ->
            try {
                val mimeTypes = arrayOf(
                    "application/msword",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document",  // .doc & .docx
                    "application/vnd.ms-powerpoint",
                    "application/vnd.openxmlformats-officedocument.presentationml.presentation",  // .ppt & .pptx
                    "application/vnd.ms-excel",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",  // .xls & .xlsx
                    "text/plain",
                    "application/pdf",
                    "application/zip"
                )

                val intent= Intent()
                //intent.type= "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                intent.type= "*/*"
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.action= Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent,"Select"),1101)



                //val excelCollection.get()
            }catch (e: Exception) {
                e.printStackTrace()

            }
        }
        this.downloadGantt?.setOnClickListener { view ->
            getDocumentGantt(view)
        }
    }

    fun getDocumentGantt(view: View){
        try {
            var db = FirebaseFirestore.getInstance()
            val documentsExcel = db.collection("excelGantt")
            documentsExcel.get().addOnCompleteListener(OnCompleteListener <QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d("DataValueID", document.id + " => " + document.data.get("name"))
                        //this.projects.set(this.projects.size,)
                        var d:Document=
                            Document(
                                document.data["name"].toString(),
                                document.data["url"].toString(),
                                document.data["idProject"].toString(),
                                document.data["id"].toString()
                            )
                        var browserIntent: Intent?=null

                        browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(d.getURL().toString()));
                        view.context.startActivity(browserIntent)
                    }


                } else {
                    Log.d("DataValueError", "Error getting documents: ", task.exception)
                    task.exception?.printStackTrace()
                }
            })

        }catch (e:Exception){
            e.printStackTrace()

        }
    }

    fun showDialog(){
        val dialogFrag = MeetingList()
        dialogFrag.setDateMessage(this.labeDate?.text.toString())
        
        var args:Bundle= Bundle()
        args.putString("date", "yyyxxdasdasd")
        dialogFrag.arguments=args
        if(this.fm!=null){
            dialogFrag.setTargetFragment(this, 1)
            fragmentManager?.let { dialogFrag.show(it, "Hola") }

            Log.i("Fragment Manager Open", "Open")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1101 && resultCode== Activity.RESULT_OK){
            try {
                val saveUri = data?.data
                var mDialog = ProgressDialog(context)
                mDialog.setMessage("Subiendo..")
                mDialog.show()

                val documentName: String = UUID.randomUUID().toString()
                var storeRef: StorageReference
                storeRef= FirebaseStorage.getInstance().getReference("Documents")
                storeRef.putFile(saveUri!!).addOnSuccessListener {
                    mDialog.dismiss()
                    //Toast.makeText(context, "Imagen subida!", Toast.LENGTH_SHORT).show()
                    storeRef.downloadUrl.addOnSuccessListener { uri ->
                        this.notifyPath?.text="ARCHIVO CARGADO CON EXITO!!"
                        Log.i("path",uri.toString())
                        var db = FirebaseFirestore.getInstance()
                        val dbDocument = db.collection("excelGantt")

                        dbDocument.get().addOnSuccessListener { documents->
                            for(d in documents){
                                d.reference.delete()
                            }
                            var doc= Document("document",uri.toString(),"","")
                            var flag:Boolean=true

                            dbDocument.add(doc.toMap()).addOnSuccessListener { documentReference ->
                                flag=true

                            }.addOnFailureListener { e ->
                                flag=false
                            }
                            Log.i("insert",flag.toString())

                        }



                    }
                }.addOnProgressListener { taskSnapshot ->
                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    mDialog.setMessage("Subiendo: $progress%")
                }
            }catch (e:Exception){

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