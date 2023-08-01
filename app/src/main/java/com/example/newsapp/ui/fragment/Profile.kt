package com.example.newsapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.newsapp.ui.LoginPage
import com.example.newsapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.profile.*

class Profile: Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var database: FirebaseDatabase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile, container, false)
        return view
    }
    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var name = view.findViewById<TextView>(R.id.name)
        var email = view.findViewById<TextView>(R.id.email)
        var logout = view.findViewById<Button>(R.id.btnlogout)

        logout.setOnClickListener {
            mAuth.signOut()
            val intent= Intent(requireContext(), LoginPage::class.java)
            startActivity(intent)
        }

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://newsapp-77ff3-default-rtdb.firebaseio.com")
        mDbRef = database?.reference!!.child("profile")
        val user = mAuth.currentUser

        email.text = user?.email
        username.text = user?.email

        var userreference = mDbRef.child(user?.uid!!)
        userreference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                fullname.text = snapshot.child("name").value.toString()
                name.text = snapshot.child("name").value.toString()
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}

