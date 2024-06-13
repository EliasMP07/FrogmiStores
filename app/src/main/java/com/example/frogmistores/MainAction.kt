package com.example.frogmistores

/**
 * Interfaz sellada que representa las acciones posibles en la pantalla principal.
 */
sealed interface MainAction {
    data object OnUpdateTheme: MainAction
}