package com.example.newsapp.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import com.example.newsapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*


class LoginPage : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: TextView
    private  lateinit var mAuth: FirebaseAuth
    var mDbRef: DatabaseReference? = null
    var database: FirebaseDatabase? =null
    private lateinit var progressBar: ProgressBar
    private lateinit var loader: ProgressDialog
    private var authStateListener: AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setTheme(R.style.splashScreenTheme)
        setContentView(R.layout.activity_login)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance()
        authStateListener = AuthStateListener {
            val user = mAuth.currentUser
            if (user != null) {
                val intent = Intent(this@LoginPage, NewsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


        editEmail = findViewById(R.id.loginEmail)
        editPassword = findViewById(R.id.loginPassword)
        btnLogin= findViewById(R.id.loginButton)
//        btnRegister = findViewById(R.id.loginQuestion)

        login()

        loader = ProgressDialog(this)

        loginQuestion.setOnClickListener {
            val intent =  Intent(this@LoginPage, RegisterPage::class.java)
            startActivity(intent)
        }
    }

    private fun login(){
        loginButton.setOnClickListener {
            if (TextUtils.isEmpty(editEmail.text.toString())){
                editEmail.setError("Please enter email")
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(editPassword.text.toString())){
                editPassword.setError("Please enter password")
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(editEmail.text.toString(), editPassword.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this@LoginPage, NewsActivity::class.java))
                        finish()

                    }else{
                        Toast.makeText(this@LoginPage, "Registration failed", Toast.LENGTH_LONG).show()

                    }
                }
        }



    }


    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(authStateListener!!)
    }

    override fun onStop() {
        super.onStop()
        mAuth.removeAuthStateListener(authStateListener!!)
    }
}