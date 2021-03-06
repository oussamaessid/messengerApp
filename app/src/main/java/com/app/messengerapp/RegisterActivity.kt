package com.app.messengerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private   var  firebaseuserID:  String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val toolbar: Toolbar = findViewById(R.id.toolbar_register)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Register"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this@RegisterActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        mAuth = FirebaseAuth.getInstance()

        register_btn.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val username: String = username_register.text.toString()
        val email: String = email_register.text.toString()
        val password: String = password_register.text.toString()

        if (username == "") {
            Toast.makeText(this@RegisterActivity, "please write username", Toast.LENGTH_LONG).show()
        } else if (email == "") {
            Toast.makeText(this@RegisterActivity, "please write email", Toast.LENGTH_LONG).show()
        } else if (password == "") {
            Toast.makeText(this@RegisterActivity, "please write password", Toast.LENGTH_LONG).show()
        } else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                 if (task.isSuccessful){
                     firebaseuserID = mAuth.currentUser!!.uid
                     refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseuserID)

                     val userHashMap= HashMap<String, Any>()
                     userHashMap["uid"]=firebaseuserID
                     userHashMap["username"]= username
                     userHashMap["profile"]= "https://firebasestorage.googleapis.com/v0/b/messengerapp-4fbdf.appspot.com/o/profile.png?alt=media&token=b0e79e5a-239a-437b-942f-6767bdeaa45a"
                     userHashMap["cover"]= "https://firebasestorage.googleapis.com/v0/b/messengerapp-4fbdf.appspot.com/o/cover.jpg?alt=media&token=9f1c6b50-bfcd-472a-8480-2d35806f0a00"
                     userHashMap["status"]= "offline"
                     userHashMap["search"]= username.toString()
                     userHashMap["facebook"]= "https://m.facebook.com"
                     userHashMap["instagram"]="https://m.instagram.com"
                     userHashMap["website"]= "https://www.google.com"

                     refUsers.updateChildren(userHashMap)
                         .addOnCompleteListener { task ->
                             if (task.isSuccessful){
                                 val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                 startActivity(intent)
                                 finish()
                             }
                     }

                 }
                else{
                     Toast.makeText(this@RegisterActivity, "Error Message "+ task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                 }
            }

        }
    }
}

