package com.example.kittynotes.utils

import java.net.PasswordAuthentication
import java.util.regex.Pattern

object Validator {
    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun isValidEmail(email: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun isValidPassword(password: String, repeatedPassword: String): Pair<Boolean, String>{
        val matching = password == repeatedPassword
        var message: String = ""

        if(!matching){
            message = "Passwords mismatch"
        }
        if(password.length < 6 && message != ""){
            message = "Password is too short"
        }

        return matching to message
    }
}