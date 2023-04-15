package com.example.kittynotes.dto

data class AuthRequest(
    val email: String?,
    val login: String?,
    val password: String?
)