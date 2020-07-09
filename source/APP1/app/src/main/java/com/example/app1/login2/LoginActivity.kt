package com.example.app1.login2

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.app1.MainActivity
import com.example.app1.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login2.*


class LoginActivity : AppCompatActivity() ,AdapterView.OnItemSelectedListener {
    var languages = arrayOf("-Seleccione una opción-", "Director", "Residente", "Dibujante","SISO","Almacenista" )
    private var mAuth: FirebaseAuth? = null
    private var email = ""
    private var password = ""
    var spinner:Spinner? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_login2)
        //val languages = resources.getStringArray(R.arra)
        //val spinner = findViewById<View>(R.id.spinner) as Spinner
        spinner = this.spinner_s
        //spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

        botton_go_home.setOnClickListener {
            email= this.nameUser.text.toString()
            password=this.passUser.text.toString()
            authentication()
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

     fun authentication(){
         var flag=1;
         mAuth!!.signInWithEmailAndPassword(email, password)
             .addOnCompleteListener(
                 this
             ) { task ->
                 if (task.isSuccessful) {
                     // Sign in success, update UI with the signed-in user's information
                     //Log.d(LoginActivity.TAG, "signInWithEmail:success")
                     Log.d("ok","bienn ok**************")
                     this.nom.text="CONECTADO"
                     val user = mAuth!!.currentUser
                     flag=0
                     //updateUI(user)
                 } else {
                    Log.d("No ok", "bad++++++++++++++++++++++")
                     // If sign in fails, display a message to the user.
                    /* Log.w(FragmentActivity.TAG, "signInWithEmail:failure", task.exception)
                     Toast.makeText(
                         this@EmailPasswordActivity, "Authentication failed.",
                         Toast.LENGTH_SHORT
                     ).show()*/
                     this.nom.text="NO CONECTADO"
                     //updateUI(null)
                 }

                 // ...
             }
         Log.d(flag.toString(),"resultado")
     }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}
