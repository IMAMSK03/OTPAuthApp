package com.example.authapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.authapp.viewmodel.AuthViewModel

@Composable
fun AuthScreen(viewModel: AuthViewModel) {

    val state by viewModel.uiState.collectAsState()

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()              // 🔥 IMPORTANT
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Login with Email OTP",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.email,
                onValueChange = viewModel::onEmailChange,
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (state.isOtpSent) {

                OutlinedTextField(
                    value = state.otp,
                    onValueChange = viewModel::onOtpChange,
                    label = { Text("OTP") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = viewModel::verifyOtp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Verify OTP")
                }

            } else {

                Button(
                    onClick = viewModel::sendOtp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Send OTP")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            state.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
