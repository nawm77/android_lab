package com.nawm.android_labs.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nawm.android_labs.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart called in MainActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume called in MainActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause called in MainActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop called in MainActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy called in MainActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "onRestart called in MainActivity")
    }
}