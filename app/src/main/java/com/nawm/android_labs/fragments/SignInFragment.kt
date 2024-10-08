package com.nawm.android_labs.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nawm.android_labs.R
import com.nawm.android_labs.domain.User
import com.nawm.android_labs.utils.RegistrationUtils

class SignInFragment : Fragment() {

    private lateinit var userNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private val users: MutableMap<String, String> = mutableMapOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        emailInput = view.findViewById(R.id.email_input)
        userNameInput = view.findViewById(R.id.name_input)
        passwordInput = view.findViewById(R.id.password_input)
        val signInButton = view.findViewById<Button>(R.id.signin_button)
        val signUpButton = view.findViewById<Button>(R.id.signup_button)

        // Добавляем тестового пользователя
        val validEmail = "test@example.com"
        val validPassword = "password123"
        users[validEmail] = validPassword

        signInButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (RegistrationUtils.isEmailValid(email)) {
                if (password == users[email]) {
                    // Переход на другой фрагмент (например, MainFragment)
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MainFragment())
                        .addToBackStack(null)
                        .commit()
                } else {
                    Toast.makeText(requireContext(), "Неверный email или пароль", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Неверный формат адреса почты", Toast.LENGTH_SHORT).show()
            }
        }

        signUpButton.setOnClickListener {
            // Открываем SignUpFragment для регистрации
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUpFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == AppCompatActivity.RESULT_OK) {
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
}