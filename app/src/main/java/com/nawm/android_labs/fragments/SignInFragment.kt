package com.nawm.android_labs.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nawm.android_labs.databinding.FragmentSignInBinding
import com.nawm.android_labs.utils.RegistrationUtils

class SignInFragment : Fragment() {
    private lateinit var userNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private val users: MutableMap<String, String> = mutableMapOf()
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("Binding is accessed before it is initialized or after it is destroyed")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        emailInput = binding.emailInput
        userNameInput = binding.nameInput
        passwordInput = binding.passwordInput
        val signInButton = binding.signinButton
        val signUpButton = binding.signupButton

        arguments?.let { args ->
            val user = SignInFragmentArgs.fromBundle(args).user
            user?.let {
                userNameInput.setText(it.userName)
                emailInput.setText(it.email)
                passwordInput.setText(it.password)
                users[it.email] = it.password
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
                    val action = SignInFragmentDirections.navigateFromSignInToChat()
                    findNavController().navigate(action)
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
            val action = SignInFragmentDirections.navigateFromSignInToSignUp()
            findNavController().navigate(action)
        }

        return binding.root
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