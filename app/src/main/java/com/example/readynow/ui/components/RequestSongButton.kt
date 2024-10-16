package com.example.readynow.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.readynow.common.RequestSongAlertDialog

@Composable
fun RequestSongButton() {
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        RequestSongAlertDialog(
            showDialog = showDialog.value,
            onDismiss = {showDialog.value = false})
    }
    ExtendedFloatingActionButton(
        onClick = { showDialog.value = true },
        icon = {Icon(Icons.Default.Edit, "Extended floating action button.") },
        text = { Text(" Request Song")}
    )
}