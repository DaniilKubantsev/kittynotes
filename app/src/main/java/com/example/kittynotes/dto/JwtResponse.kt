package com.example.kittynotes.dto

data class JwtResponse(
    private val accessToken: String?,
    private val refreshToken: String?
) {
    private val type = "Bearer"

}