package com.example.app1.login2

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
            this.email= this.nameUser.text.toString()
            this.password=this.passUser.text.toString()

            if(email!=null && email.length>0){
                if(password!=null && password.length>0){

                    authentication()

                }else{
                    this.msg.text="Por favor digite una contraseña"
                }

            }else{
                this.msg.text="Por favor digite un Usuario"
            }


        }

    }

     fun authentication(){
         try {
             Log.d("auth","Here")
             mAuth!!.signInWithEmailAndPassword(this.email, this.password)
                 .addOnCompleteListener(
                     this
                 ) { task ->
                     if (task.isSuccessful) {
                         // Sign in success, update UI with the signed-in user's information
                         Log.d("AUTHENTICATION", "signInWithEmail:success")
                         val user = mAuth!!.currentUser
                         startActivity(Intent(this,MainActivity::class.java))
                     } else {
                         // If sign in fails, display a message to the user.
                         /*Log.w(
                             FragmentActivity.TAG,
                             "signInWithEmail:failure",
                             task.exception
                         )
                         //Toast.makeText(
                         //    this@EmailPasswordActivity, "Authentication failed.",
                         //    Toast.LENGTH_SHORT
                         //).show()
                         //updateUI(null)*/
                         Log.d("AUTHENTICATION", "signInWithEmail:error")
                         this.msg.text="Usuario y/o contraseña no válido"
                     }

                     // ...
                 }
         }catch (e:Exception){
             Log.getStackTraceString(e)

         }


     }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}
