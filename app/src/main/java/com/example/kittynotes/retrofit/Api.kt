package com.example.kittynotes.retrofit

import com.example.kittynotes.dto.AuthRequest
import com.example.kittynotes.dto.JwtResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("api/v1/login")
    suspend fun auth(@Body authRequest: AuthRequest): JwtResponse
}