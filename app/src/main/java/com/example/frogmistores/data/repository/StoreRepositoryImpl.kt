@file:OptIn(ExperimentalPagingApi::class)

package com.example.frogmistores.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.frogmistores.data.local.database.FrogmiStoreDatabase
import com.example.frogmistores.data.mappers.toStore
import com.example.frogmistores.data.paging.FrogmiStoresRemoteMediator
import com.example.frogmistores.data.remote.FrogmiStoresApi
import com.example.frogmistores.data.utils.Const
import com.example.frogmistores.domain.model.Store
import com.example.frogmistores.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Implementación del repositorio de tiendas que utiliza una API remota y una base de datos local.
 * @property frogmiStoresApi Instancia del servicio de API FrogmiStoresApi.
 * @property storeDatabase Instancia de la base de datos FrogmiStoreDatabase.
 */
class StoreRepositoryImpl(
    private val frogmiStoresApi: FrogmiStoresApi,
    private val storeDatabase: FrogmiStoreDatabase
) : StoreRepository {

    /**
     * Obtiene todas las tiendas en un flujo paginado.
     * @return Un flujo de datos paginados de tiendas.
     */
    override fun getAllStores(): Flow<PagingData<Store>> {
        val pagingSourceFactory = {
            storeDatabase.dao.pagingSource()
        }
        return flow {
            try {
                emitAll(
                    Pager(
                        config = PagingConfig(pageSize = Const.ITEMS_PER_PAGE),
                        remoteMediator = FrogmiStoresRemoteMediator(
                            storeDb = storeDatabase,
                            frogmiStoreApi = frogmiStoresApi
                        ),
                        pagingSourceFactory = pagingSourceFactory
                    ).flow.map { pagingData ->
                        pagingData.map { storeEntity ->
                            storeEntity.toStore()
                        }
                    }
                )
            } catch (e: Exception) {
                emit(PagingData.empty())
                e.printStackTrace()
            }
        }
    }

}