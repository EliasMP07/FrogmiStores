package com.example.frogmistores.presentation.storeDetail

import com.example.frogmistores.domain.model.Store

/**
 * Clase de estado que representa el estado de la pantalla de detalles de la tienda.
 * @property store El objeto Store cuyos detalles se están mostrando.
 */
data class StoreDetailState(
    val store: Store = Store()
)
