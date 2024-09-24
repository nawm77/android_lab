package com.nawm.android_labs.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nawm.android_labs.R

class OnboardActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard_activity)
        val nextButton = findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener { view: View? ->
            val intent =
                Intent(
                    this@OnboardActivity,
                    SignInActivity::class.java
                )
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart called in OnboardActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume called in OnboardActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause called in OnboardActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop called in OnboardActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy called in OnboardActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "onRestart called in OnboardActivity")
    }
}