package com.example.kittynotes

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kittynotes.dto.AuthRequest

import com.example.kittynotes.retrofit.ApiService

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restApi = ApiService()

        val regButton = findViewById<Button>(R.id.registration_button)
        val signInButton = findViewById<Button>(R.id.sign_in_button)






        regButton.setOnClickListener{
            setContentView(R.layout.activity_registration)
        }


        signInButton.setOnClickListener{
            var login = getString(R.id.username_form).toString()
            var password = getString(R.id.password_form).toString()

            val authRequest = AuthRequest(login, password)

            restApi.authorisation(authRequest)
        }
    }
}

