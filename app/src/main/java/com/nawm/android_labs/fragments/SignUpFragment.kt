package com.nawm.android_labs.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nawm.android_labs.databinding.FragmentSignUpBinding
import com.nawm.android_labs.domain.User
import com.nawm.android_labs.utils.RegistrationUtils

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("Binding is accessed before it is initialized or after it is destroyed")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.signupButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val name = binding.nameInput.text.toString()

            if (RegistrationUtils.isEmailValid(email)) {
                val user = User(name, email, password)

                val action = SignUpFragmentDirections.navigateFromSignUpToSignIn(user)
                findNavController().navigate(action)

                Toast.makeText(
                    requireContext(),
                    "Регистрация прошла успешно",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Неверный формат адреса почты",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
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