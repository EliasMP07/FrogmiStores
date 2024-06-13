package com.example.frogmistores.core.presentation.ui.utils

/**
 * Clase sellada que representa el estado de la conectividad.
 */
sealed class ConnectionState{
    data object Available : ConnectionState()
    data object Unavailable : ConnectionState()
}