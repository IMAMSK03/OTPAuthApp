package com.example.authapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import com.example.authapp.screens.AuthScreen
import com.example.authapp.screens.SessionScreen
import com.example.authapp.ui.theme.AuthAppTheme
import com.example.authapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AuthAppTheme {

                val authViewModel: AuthViewModel = viewModel()
                val state = authViewModel.uiState.collectAsState().value

                if (state.isLoggedIn && state.sessionStartTime != null) {
                    // ✅ SESSION TRACKER SCREEN
                    SessionScreen(
                        startTime = state.sessionStartTime,
                        onLogout = { authViewModel.onLogout() }
                    )
                } else {
                    // ✅ EMAIL + OTP SCREEN
                    AuthScreen(viewModel = authViewModel)
                }
            }
        }
    }
}
