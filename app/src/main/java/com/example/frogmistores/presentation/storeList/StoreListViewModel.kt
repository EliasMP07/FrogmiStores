package com.example.frogmistores.presentation.storeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.frogmistores.domain.GetAllStoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel para la pantalla de la lista de tiendas.
 * Utiliza Hilt para la inyección de dependencias.
 *
 * @property getAllStoresUseCase Caso de uso para obtener todas las tiendas.
 */
@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val getAllStoresUseCase: GetAllStoresUseCase
): ViewModel(){

    // Flujo de paginación de tiendas, obtenido del caso de uso y almacenado en cache dentro del ViewModelScope.
    val storePaggingFlow = getAllStoresUseCase().cachedIn(viewModelScope)

}