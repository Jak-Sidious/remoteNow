package com.example.readynow.ui.interfaces

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.readynow.common.ErrorDialog
import com.example.readynow.data.model.SongRequest
import com.example.readynow.ui.viewModel.PaymentUiState
import com.example.readynow.ui.viewModel.PaymentViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentScreen(viewModel: PaymentViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val songRequest = remember { SongRequest("Song Title", "Artist Name") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Request Song: ${songRequest.title}",
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.processPayment(songRequest) },
            enabled = uiState !is PaymentUiState.Loading
        ) {
            Text("Process Payment")
        }

        when (uiState) {
            is PaymentUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is PaymentUiState.Success -> {
                val result = (uiState as PaymentUiState.Success).paymentResult
                Text(
                    text = "${result.message}\nAmount: $${result.amount}\nDate: ${result.formattedDate}",
                    color = Color.Green,
                    modifier = Modifier.padding(16.dp)
                )
            }
            is PaymentUiState.Error -> {
                ErrorDialog(
                    errorMessage = (uiState as PaymentUiState.Error).errorMessage,
                    onDismiss = { viewModel.dismissError() },
                    onRetry = { viewModel.retryPayment(songRequest) }
                )
            }
            PaymentUiState.Idle -> {
                // No-op
            }
        }
    }
}