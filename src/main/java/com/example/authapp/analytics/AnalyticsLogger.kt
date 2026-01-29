package com.example.authapp.analytics

import timber.log.Timber

object AnalyticsLogger {

    fun logOtpGenerated(email: String) {
        Timber.d("OTP generated for $email")
    }

    fun logOtpSuccess(email: String) {
        Timber.d("OTP success for $email")
    }

    fun logOtpFailure(email: String) {
        Timber.d("OTP failed for $email")
    }

    fun logLogout() {
        Timber.d("User logged out")
    }
}
