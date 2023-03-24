package com.example.kittynotes.retrofit

import com.example.kittynotes.R
import com.example.kittynotes.dto.AuthRequest
import com.example.kittynotes.dto.JwtResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiService {
    val api = ServiceBuilder().getApi()

    fun authorisation(authRequest: AuthRequest){
        CoroutineScope(Dispatchers.IO).launch {
            val jwt = api.auth(authRequest)
        }
    }
}