package com.example.authapp.viewmodel

data class AuthState(
    val email: String = "",
    val otp: String = "",
    val isOtpSent: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String? = null,
    val sessionStartTime: Long? = null
)
