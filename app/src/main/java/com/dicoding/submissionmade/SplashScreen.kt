package com.dicoding.submissionmade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dicoding.submissionmade.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, 2500)
    }
}