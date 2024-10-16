package com.example.readynow.common

sealed interface NetworkConnectionState {
    data object Available : NetworkConnectionState
    data object Unavailable: NetworkConnectionState
}