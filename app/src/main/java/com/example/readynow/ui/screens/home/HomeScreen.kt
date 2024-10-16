package com.example.readynow.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readynow.HomeSection
import com.example.readynow.ui.components.QueuedSongsList
import com.example.readynow.ui.components.SearchBar
import com.example.readynow.ui.screens.network.NetworkScreen
import com.example.readynow.ui.theme.ReadyNowTheme

// Step: Home screen - Scrolling
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(16.dp))
        HomeSection(title = "Queued Songs") {
            QueuedSongsList()
        }
        Spacer(Modifier.height(16.dp))
        NetworkScreen()

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ScreenContentPreview() {
    ReadyNowTheme { HomeScreen() }
}