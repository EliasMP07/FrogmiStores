package com.example.frogmistores.domain

import androidx.paging.PagingData
import com.example.frogmistores.domain.model.Store
import com.example.frogmistores.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso para obtener todas las tiendas.
 * Utiliza el repositorio para acceder a los datos paginados de las tiendas.
 *
 * @property repository Instancia del repositorio StoreRepository.
 */
class GetAllStoresUseCase(
    private val repository: StoreRepository
) {
    /**
     * Operador invoke para ejecutar el caso de uso.
     * @return Un flujo de datos paginados de tiendas.
     */
    operator fun invoke(): Flow<PagingData<Store>> = repository.getAllStores()
}