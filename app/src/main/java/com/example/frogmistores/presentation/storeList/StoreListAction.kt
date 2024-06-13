package com.example.frogmistores.presentation.storeList

import com.example.frogmistores.domain.model.Store

/**
 * Interfaz sellada que representa las acciones posibles en la pantalla de la lista de tiendas.
 */
sealed interface StoreListAction {

    data class OnStoreDetailClick(val store: Store): StoreListAction

    data object OnUpdateTheme: StoreListAction

}