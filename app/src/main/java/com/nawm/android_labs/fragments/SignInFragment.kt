package com.nawm.android_labs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nawm.android_labs.R
import com.nawm.android_labs.activities.MainActivity
import com.nawm.android_labs.utils.RegistrationUtils

class SignInFragment : Fragment() {
    private lateinit var userNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private val users: MutableMap<String, String> = mutableMapOf("test@example.com" to "password123")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        emailInput = view.findViewById(R.id.email_input)
        userNameInput = view.findViewById(R.id.name_input)
        passwordInput = view.findViewById(R.id.password_input)
        val signInButton = view.findViewById<Button>(R.id.signin_button)
        val signUpButton = view.findViewById<Button>(R.id.signup_button)

        signInButton.setOnClickListener {
            handleSignIn()
        }

        signUpButton.setOnClickListener {
            (requireActivity() as MainActivity).openSignUp()
        }

        return view
    }

    private fun handleSignIn() {
        val email = emailInput.text.toString().trim()
        val username = userNameInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()

        if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            showToast("Заполните все поля")
            return
        }

        if (!RegistrationUtils.isEmailValid(email)) {
            showToast("Неверный формат адреса почты")
            return
        }

        if (password == users[email]) {
            (requireActivity() as MainActivity).openChat()
        } else {
            showToast("Неверный email или пароль")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}