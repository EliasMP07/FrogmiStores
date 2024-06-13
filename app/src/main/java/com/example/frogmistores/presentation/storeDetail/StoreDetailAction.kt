package com.example.frogmistores.presentation.storeDetail

/**
 * Interfaz sellada que representa las acciones posibles en la pantalla de detalles de la tienda.
 */
sealed interface StoreDetailAction{

    data object OnBackClick: StoreDetailAction

}