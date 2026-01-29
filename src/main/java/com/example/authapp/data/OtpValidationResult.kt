package com.example.authapp.data

sealed class OtpValidationResult {
    object Success : OtpValidationResult()
    object Failure : OtpValidationResult()
}
