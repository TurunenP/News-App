package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.ui.LoginPage


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val splashIntent = Intent(this, LoginPage::class.java)
            startActivity(splashIntent)
            finish()
        }, 2000)

    }
}
