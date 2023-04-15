package com.example.kittynotes.retrofit

import com.example.kittynotes.dto.AuthRequest
import com.example.kittynotes.dto.JwtResponse
import kotlinx.coroutines.*
import retrofit2.HttpException

object ApiService {
    val api = ServiceBuilder().getApi()

    suspend fun authorisation(authRequest: AuthRequest): JwtResponse? {
        return api.auth(authRequest)
    }

    suspend fun registration(registrationRequest: AuthRequest){
        return api.registration(registrationRequest)
    }
}