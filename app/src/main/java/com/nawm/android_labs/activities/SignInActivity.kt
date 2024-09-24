package com.nawm.android_labs.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nawm.android_labs.R
import com.nawm.android_labs.domain.User
import com.nawm.android_labs.utils.RegistrationUtils

class SignInActivity : AppCompatActivity() {
    private lateinit var userNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private val users: MutableMap<String, String> = mutableMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        emailInput = findViewById<EditText>(R.id.email_input)
        userNameInput = findViewById<EditText>(R.id.name_input)
        passwordInput = findViewById<EditText>(R.id.password_input)
        val signInButton = findViewById<Button>(R.id.signin_button)
        val signUpButton = findViewById<Button>(R.id.signup_button)

        signInButton.setOnClickListener { view: View? ->
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val validEmail = "test@example.com"
            val validPassword = "password123"
            users[validEmail] = validPassword
            if(RegistrationUtils.isEmailValid(email)) {
                if (password == users[email]) {
                    val intent =
                        Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignInActivity, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@SignInActivity, "Неверный формат адреса почты", Toast.LENGTH_SHORT).show()
            }
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Вариант String
//            val name = data?.getStringExtra("name")
//            val email = data?.getStringExtra("email")
//            val password = data?.getStringExtra("password")
//            users[email.toString()] = password.toString()
//            userNameInput.setText(name)
//            emailInput.setText(email)
//            passwordInput.setText(password)

            // Вариант с передачей объекта User
            val user = data?.getSerializableExtra("user") as? User
            user?.let {
                userNameInput.setText(it.userName)
                emailInput.setText(it.email)
                passwordInput.setText(it.password)
                users[it.email] = it.password
            }
        }
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