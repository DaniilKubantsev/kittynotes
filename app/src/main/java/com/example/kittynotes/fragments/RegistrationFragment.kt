package com.example.kittynotes.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kittynotes.R
import com.example.kittynotes.dto.AuthRequest
import com.example.kittynotes.retrofit.ApiService
import com.example.kittynotes.utils.Validator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegistrationFragment : Fragment() {
    private lateinit var view: View
    private lateinit var registrationBtn: Button
    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatedPasswordEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_registration, container, false)
        registrationBtn = view.findViewById(R.id.regitration_button)

        registrationBtn.setOnClickListener{
            onRegistrationClick(view)
        }

        return view
    }

    private fun onRegistrationClick(view: View){
        emailEditText = view.findViewById(R.id.email_edit_text)
        usernameEditText = view.findViewById(R.id.username_edit_text)
        passwordEditText = view.findViewById(R.id.password_edit_text)
        repeatedPasswordEditText = view.findViewById(R.id.repeat_password_edit_text)

        val email = emailEditText.text.toString()
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        val repeatedPassword = repeatedPasswordEditText.text.toString()

        val isValidPassword = Validator.isValidPassword(password, repeatedPassword)

        if(!Validator.isValidEmail(email)){
            emailEditText.setText("")
            emailEditText.hint = "Wrong email!"
        }
        if(Validator.isValidEmail(username)){
            usernameEditText.setText("")
            emailEditText.hint = "Wrong username!"
        }
        if(!isValidPassword.first){
            passwordEditText.setText("")
            passwordEditText.hint = isValidPassword.second
        }
        if(Validator.isValidEmail(email) && !Validator.isValidEmail(username)){
            val registrationRequest = AuthRequest(
                email,
                username,
                password
            )

            CoroutineScope(Dispatchers.IO).launch{
                try{
                    ApiService.registration(registrationRequest)
                    launch(Dispatchers.Main) {
                        Toast.makeText(activity, "OK", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: HttpException) {
                    val errorCode = e.code()

                    if (errorCode == 400) {
                        launch(Dispatchers.Main) {
                            Toast.makeText(activity, "BAD REQUEST", Toast.LENGTH_SHORT).show()
                        }
                    }
                    if (errorCode == 401) {
                        launch(Dispatchers.Main) {
                            Toast.makeText(activity, "Пользователь уже зарегистрирован", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }


}