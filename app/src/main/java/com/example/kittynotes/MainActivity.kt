package com.example.kittynotes

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kittynotes.dto.AuthRequest
import com.example.kittynotes.retrofit.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val regButton: Button = findViewById(R.id.registration_button)
        val signInButton: Button = findViewById(R.id.sign_in_button)
        val authRequest = AuthRequest(getText(R.id.username_form).toString(), getText(R.id.username_form).toString())

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val clien = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://kaplaan.ru/backend/app/")
            .client(clien)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)



        regButton.setOnClickListener{
            setContentView(R.layout.activity_registration)
        }

        signInButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val jwt = api.auth(authRequest)
            }
        }
    }
}

