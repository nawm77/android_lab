package com.nawm.android_labs.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nawm.android_labs.R
import com.nawm.android_labs.domain.User
import com.nawm.android_labs.fragments.ChatFragment
import com.nawm.android_labs.fragments.OnboardFragment
import com.nawm.android_labs.fragments.SignInFragment
import com.nawm.android_labs.fragments.SignUpFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateToOnboard()
        }
    }

    fun navigateToSignIn() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SignInFragment())
            .commit()
    }

    fun navigateToSignUp() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SignUpFragment())
            .commit()
    }

    fun navigateToChat() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ChatFragment())
            .addToBackStack(null)
            .commit()
    }

    fun navigateToOnboard() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, OnboardFragment())
            .commit()
    }

    fun onUserRegistered(user: User) {
        Log.d("MainActivity", "User registered: $user")
        val signInFragment = SignInFragment()
        val args = Bundle()
        args.putSerializable("user", user)
        signInFragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, signInFragment)
            .addToBackStack(null)
            .commit()
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