/*
    SplashActivity.kt
    Ethan Pitzer
    2021-13-3

    SplashActivity is a simple landing page which starts at the start of the application, and
    provides a quick visual info graphic about my application and its name. The page is displayed
    for a short period, where a transition is then called to the main activity class, where
    primary application behavior is sourced from
 */
package com.example.pitzertermproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed ({
            Log.i("Main","timer done")
            startActivity(intent)
        },5000)
    }
}