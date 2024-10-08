package com.nawm.android_labs.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nawm.android_labs.R
import com.nawm.android_labs.fragments.ChatFragment
import com.nawm.android_labs.fragments.OnboardFragment
import com.nawm.android_labs.fragments.SignInFragment
import com.nawm.android_labs.fragments.SignUpFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openSignIn()
        }
    }

    fun openSignIn(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SignInFragment())
            .commit()
    }

    fun openSignUp(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SignUpFragment())
            .commit()
    }

    fun openOnboard(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, OnboardFragment())
            .commit()
    }

    fun openChat(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ChatFragment())
            .commit()
    }
}