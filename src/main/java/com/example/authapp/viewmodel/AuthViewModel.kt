package com.example.authapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.authapp.analytics.AnalyticsLogger
import com.example.authapp.data.OtpManager
import com.example.authapp.data.OtpValidationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onOtpChange(otp: String) {
        _uiState.update { it.copy(otp = otp) }
    }

    fun sendOtp() {
        val email = _uiState.value.email
        if (email.isBlank()) {
            _uiState.update { it.copy(error = "Email required") }
            return
        }
        OtpManager.generateOtp(email)
        AnalyticsLogger.logOtpGenerated(email)
        _uiState.update { it.copy(isOtpSent = true, error = null) }
    }

    fun verifyOtp() {
        val state = _uiState.value
        when (OtpManager.validateOtp(state.email, state.otp)) {
            OtpValidationResult.Success -> {
                AnalyticsLogger.logOtpSuccess(state.email)
                _uiState.update {
                    it.copy(
                        isLoggedIn = true,
                        sessionStartTime = System.currentTimeMillis(),
                        error = null
                    )
                }
            }
            else -> {
                AnalyticsLogger.logOtpFailure(state.email)
                _uiState.update { it.copy(error = "Invalid / Expired OTP") }
            }
        }
    }

    fun onLogout() {
        AnalyticsLogger.logLogout()
        _uiState.value = AuthState()
    }
}
