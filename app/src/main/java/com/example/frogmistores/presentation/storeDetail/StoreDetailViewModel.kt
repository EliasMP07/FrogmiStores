package com.example.frogmistores.presentation.storeDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.frogmistores.domain.model.Store
import com.example.frogmistores.navigation.utils.NavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * ViewModel para la pantalla de detalles de la tienda.
 * Utiliza Hilt para la inyección de dependencias.
 *
 * @property savedStateHandle Manejador de estado guardado, utilizado para recuperar y almacenar el estado.
 */
@HiltViewModel
class StoreDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel(){

    private val _state = MutableStateFlow(StoreDetailState())
    val state: StateFlow<StoreDetailState> get() =_state.asStateFlow()

    init {
        // Recupera el objeto Store del savedStateHandle usando la clave proporcionada en NavArgs.
        val store: Store = savedStateHandle[NavArgs.StoreID.key]!!
        _state.update {
            it.copy(
                store = store
            )
        }
    }
}