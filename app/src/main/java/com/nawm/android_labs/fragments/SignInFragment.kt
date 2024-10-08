package com.nawm.android_labs.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nawm.android_labs.R
import com.nawm.android_labs.domain.User
import com.nawm.android_labs.utils.RegistrationUtils
import com.nawm.android_labs.activities.MainActivity

class SignInFragment : Fragment() {
    private lateinit var userNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private val users: MutableMap<String, String> = mutableMapOf()

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
        arguments?.getSerializable("user")?.let { user ->
            if (user is User) {
                userNameInput.setText(user.userName)
                emailInput.setText(user.email)
                passwordInput.setText(user.password)
                users[user.email] = user.password
            }
        }
        signInButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val validEmail = "test@example.com"
            val validPassword = "password123"
            users[validEmail] = validPassword

            if (RegistrationUtils.isEmailValid(email)) {
                if (password == users[email]) {
                    (requireActivity() as MainActivity).navigateToChat()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Неверный email или пароль",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Неверный формат адреса почты",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        signUpButton.setOnClickListener {
            (requireActivity() as MainActivity).navigateToSignUp()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart called in SignInFragment")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume called in SignInFragment")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause called in SignInFragment")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop called in SignInFragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy called in SignInFragment")
    }
}