package com.example.authapp.data

import android.util.Log

object OtpManager {

    private val otpStore = mutableMapOf<String, String>()

    fun generateOtp(email: String) {
        val otp = (100000..999999).random().toString()
        otpStore[email] = otp

        // 👇 THIS IS THE MOST IMPORTANT LINE
        Log.d("OTP", "OTP for $email is $otp")
    }

    fun validateOtp(email: String, otp: String): OtpValidationResult {
        val storedOtp = otpStore[email]
        return if (storedOtp == otp) {
            otpStore.remove(email)
            OtpValidationResult.Success
        } else {
            OtpValidationResult.Failure
        }
    }
}
