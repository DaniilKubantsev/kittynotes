package com.example.kittynotes.dto

data class JwtResponse(
    private val accessToken: String?,
    private val refreshToken: String?,
    private val type: String = "Bearer"
){
    fun getAccessToken() = accessToken
    fun getRefreshToken() = refreshToken
    fun getType() = type
}