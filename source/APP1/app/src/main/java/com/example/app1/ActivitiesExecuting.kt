package com.example.app1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_login2.*
import kotlinx.android.synthetic.main.fragment_activities_executing.*

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
    var projects=arrayOf("-Seleccione una opción-" )
    var spinnerp: Spinner? = null
    var spinners: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         spinnerp =this.projects_works
         spinners=this.states_spinner

         /*val aa = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, projects)
         aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
         spinnerp!!.setAdapter(aa)

         val aa2 = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, states)
         aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
         spinners!!.setAdapter(aa2)*/
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activities_executing, container, false)
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