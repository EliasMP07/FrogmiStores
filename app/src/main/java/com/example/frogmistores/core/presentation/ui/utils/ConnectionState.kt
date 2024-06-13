package com.example.frogmistores.core.presentation.ui.utils

sealed class ConnectionState{
    data object Available : ConnectionState()
    data object Unavailable : ConnectionState()
}