package com.nawm.android_labs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger

class SignInActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val signInButton = findViewById<Button>(R.id.signin_button)

        signInButton.setOnClickListener { view: View? ->
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            val validEmail = "test@example.com"
            val validPassword = "password123"
            if(isEmailValid(email)) {
                if (email == validEmail && password == validPassword) {
                    val intent =
                        Intent(
                            this@SignInActivity,
                            OnboardActivity::class.java
                        )
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@SignInActivity,
                        "Неверный email или пароль",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@SignInActivity,
                    "Неверный формат адреса почты",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegexp = ".*@.*\\..*"
        val regexp = Regex(emailRegexp)
        return email.matches(regexp)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart called in SignInActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume called in SignInActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause called in SignInActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop called in SignInActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy called in SignInActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "onRestart called in SignInActivity")
    }
}