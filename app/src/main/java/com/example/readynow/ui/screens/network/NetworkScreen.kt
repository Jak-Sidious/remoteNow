package com.example.readynow.ui.screens.network

import android.view.Gravity
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.readynow.common.NetworkConnectionState
import com.example.readynow.common.rememberConnectivityState

@Composable
fun NetworkScreen() {
    val connectionState by rememberConnectivityState()
    val context = LocalContext.current

    val isConnected by remember(connectionState) {
        derivedStateOf {
            connectionState === NetworkConnectionState.Available
        }
    }



    if (isConnected) {
        val toast = Toast.makeText(context, "You are connected", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0, 0)
        toast.show()
    } else {
        val toast = Toast.makeText(context, "You are offline", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0, 0)
        toast.show()
    }
}