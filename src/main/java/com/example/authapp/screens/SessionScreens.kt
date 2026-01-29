package com.example.authapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SessionScreen(startTime: Long, onLogout: () -> Unit) {

    var duration by remember { mutableStateOf(0L) }

    LaunchedEffect(Unit) {
        while (true) {
            duration = (System.currentTimeMillis() - startTime) / 1000
            delay(1000)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Session Time: ${duration}s")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onLogout) {
            Text("Logout")
        }
    }
}
