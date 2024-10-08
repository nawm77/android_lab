package com.nawm.android_labs.fragments

import android.content.Context
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

class SignUpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val emailInput = view.findViewById<EditText>(R.id.email_input)
        val nameInput = view.findViewById<EditText>(R.id.name_input)
        val passwordInput = view.findViewById<EditText>(R.id.password_input)
        val signUpButton = view.findViewById<Button>(R.id.signup_button)

        signUpButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val name = nameInput.text.toString()
            if (RegistrationUtils.isEmailValid(email)) {
                val user = User(name, email, password)
                (requireActivity() as MainActivity).onUserRegistered(user)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Неверный формат адреса почты",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart called in SignUpFragment")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume called in SignUpFragment")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause called in SignUpFragment")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop called in SignUpFragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy called in SignUpFragment")
    }
}