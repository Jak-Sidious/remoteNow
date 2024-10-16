package com.example.readynow.common


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.readynow.ui.theme.ReadyNowTheme

@Composable
fun RequestSongAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            text = {
                Text(text = "Would You like to request a song")
            },
            onDismissRequest = onDismiss,
            confirmButton = { TextButton(
                onClick = { onDismiss }) {
                    Text("Yes")
                }
            },
            dismissButton = { TextButton (onClick = onDismiss){
                    Text("No")
            }
            }
        )
    }

}

@Preview
@Composable
fun RequestSongAlertDialogPreview() {
    ReadyNowTheme {
        RequestSongAlertDialog(showDialog = true) {

        }
    }
}