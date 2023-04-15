package com.example.kittynotes.fragments

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
import com.example.kittynotes.dto.JwtResponse
import com.example.kittynotes.retrofit.ApiService
import com.example.kittynotes.utils.Validator
import kotlinx.coroutines.*
import retrofit2.HttpException

class SignInFragment : Fragment() {

    private lateinit var view: View
    private lateinit var signInBtn: Button
    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        signInBtn = view.findViewById(R.id.sign_in_button)

        signInBtn.setOnClickListener {
            onSignInClick(view)
        }

        return view
    }

    private fun onSignInClick(view: View): JwtResponse? {
        loginEditText = view.findViewById(R.id.login_edit_text)
        passwordEditText = view.findViewById(R.id.password_edit_text)

        val login = loginEditText.text.toString()
        val password = passwordEditText.text.toString()
        val authRequestBody: AuthRequest

        if (Validator.isValidEmail(login)) {
            authRequestBody = AuthRequest(
                login,
                null,
                password
            )
        } else {
            authRequestBody = AuthRequest(
                null,
                login,
                password
            )
        }


        var authResult = CoroutineScope(Dispatchers.Default).async {
            try {
                ApiService.authorisation(authRequestBody)

//                launch(Dispatchers.Main) {
//                    Toast.makeText(activity, "OK", Toast.LENGTH_SHORT).show()
//                }
            } catch (e: HttpException) {
                if (e.code() == 400) {
                    null
//                    launch(Dispatchers.Main) {
//                        Toast.makeText(
//                            activity,
//                            "BAD REQUEST",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
                } else if(e.code() == 401){
                    null
//                    launch(Dispatchers.Main) {
//                        Toast.makeText(
//                            activity,
//                            "UNAUTHORIZED",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
                }
                else{
                    null
                }

            }
        }
        authResult.invokeOnCompletion {
            if(it == null){
                authResult.getCompleted()
            }
        }

        return JwtResponse(null, null)
    }

}