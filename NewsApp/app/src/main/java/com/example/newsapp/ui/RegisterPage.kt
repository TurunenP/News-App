package com.example.newsapp.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*


class RegisterPage : AppCompatActivity() {


    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: TextView
    private lateinit var mAuth: FirebaseAuth
    var mDbRef: DatabaseReference? = null
    var database: FirebaseDatabase? =null
    private lateinit var loader: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mDbRef = database?.reference!!.child("profile")

        register()

        editEmail = findViewById(R.id.registerEmail)
        editPassword = findViewById(R.id.signupPassword)
        btnLogin = findViewById(R.id.signupPageQuesions)

        signuppPageQuestions.setOnClickListener {
            startActivity(Intent(this@RegisterPage, LoginPage::class.java))
        }
//        btnRegister = findViewById(R.id.registerButton)
        loader = ProgressDialog(this)
    }


    private fun register() {
        registerButton.setOnClickListener {

            if (TextUtils.isEmpty(regName.text.toString())) {
                regName.setError("Please enter the  Your name")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(editEmail.text.toString())) {
                editEmail.setError("Please enter the  User name")
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(editPassword.text.toString())){
                editPassword.setError("Please enter the  password")
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(editEmail.text.toString(), editPassword.text.toString())
                .addOnCompleteListener{
                    if (it.isSuccessful){
                       val currectUser =  mAuth.currentUser
                        val currentUserDb = mDbRef?.child((currectUser?.uid!!))
                        currentUserDb?.child("name")?.setValue(regName.text.toString())
                        Toast.makeText(this@RegisterPage, "Registration Successfull", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(this@RegisterPage, "Registration failed", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}