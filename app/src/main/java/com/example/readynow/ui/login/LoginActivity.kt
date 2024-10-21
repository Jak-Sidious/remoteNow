package com.example.readynow.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.readynow.MainActivity
import com.example.readynow.ui.screens.signin.SignInViewModel
import com.example.readynow.ui.theme.ReadyNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReadyNowTheme {
                val signInState by viewModel.signInState.collectAsState()

                LoginScreen(
                    viewModel = viewModel,
                    onSignInSuccess = {
                        // Navigate to MainActivity on successful sign-in
                        startActivity(Intent(this, MainActivity::class.java))
                        finish() // Close LoginActivity
                    }
                )
            }
        }
    }
}