package com.nawm.android_labs.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nawm.android_labs.R
import com.nawm.android_labs.domain.User
import com.nawm.android_labs.utils.RegistrationUtils

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val emailInput = findViewById<EditText>(R.id.email_input)
        val nameInput = findViewById<EditText>(R.id.name_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val signUpButton = findViewById<Button>(R.id.signup_button)

        signUpButton.setOnClickListener { view: View? ->
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val name = nameInput.text.toString()
            if (RegistrationUtils.isEmailValid(email)) {
                val intent = Intent()
//                intent.putExtra("email", email)
//                intent.putExtra("password", password)
//                intent.putExtra("name", name)

                //User
                val user = User(name, email, password)
                intent.putExtra("user", user)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(
                    this@SignUpActivity,
                    "Неверный формат адреса почты",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}