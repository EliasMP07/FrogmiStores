@file:OptIn(ExperimentalPagingApi::class)

package com.example.frogmistores.data.paging

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.frogmistores.data.local.database.FrogmiStoreDatabase
import com.example.frogmistores.data.local.entities.StoreEntity
import com.example.frogmistores.data.local.entities.StoreRemoteKeys
import com.example.frogmistores.data.mappers.toStoreEntity
import com.example.frogmistores.data.remote.FrogmiStoresApi
import com.example.frogmistores.data.utils.Const
import java.io.IOException

/**
 * RemoteMediator para manejar la paginación desde una fuente remota (API) a una fuente local (base de datos).
 * @param storeDb Instancia de la base de datos FrogmiStoreDatabase.
 * @param frogmiStoreApi Instancia del servicio de API FrogmiStoresApi.
 */
class FrogmiStoresRemoteMediator(
    private val storeDb: FrogmiStoreDatabase,
    private val frogmiStoreApi: FrogmiStoresApi
) : RemoteMediator<Int, StoreEntity>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, StoreEntity>
    ): MediatorResult {
        return try {
            // Determina la página actual según el tipo de carga
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1)?:1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            // Realiza la solicitud a la API para obtener las tiendas
            val response = frogmiStoreApi.getStores(
                pageCount = Const.ITEMS_PER_PAGE,
                page = currentPage
            ).stores.map {
                it.toStoreEntity()
            }

            // Verifica si se ha alcanzado el final de la paginación
            val endOfPaginationReached = response.isEmpty()

            // Calcula las páginas anterior y siguiente
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            // Transacción de la base de datos
            storeDb.withTransaction {
                if (loadType == LoadType.REFRESH){
                    storeDb.dao.clearAll()
                    storeDb.daoKeys.deleteAllRemoteKeys()
                }

                // Crea y agrega las claves remotas a la base de datos
                val keys = response.map {store ->
                    StoreRemoteKeys(
                        id = store.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                storeDb.daoKeys.addAllRemoteKeys(keys)
                storeDb.dao.addStores(response)
            }
            // Retorna el resultado de éxito de la mediación
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    /**
     * Obtiene la clave remota más cercana a la posición actual.
     * @param state El estado de la paginación.
     * @return La clave remota más cercana a la posición actual o null si no se encuentra.
     */
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, StoreEntity>
    ): StoreRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                storeDb.daoKeys.getRemoteKeys(id = id)
            }
        }
    }

    /**
     * Obtiene la clave remota para el primer elemento en la lista.
     * @param state El estado de la paginación.
     * @return La clave remota para el primer elemento o null si no se encuentra.
     */
    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, StoreEntity>
    ): StoreRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                storeDb.daoKeys.getRemoteKeys(id = unsplashImage.id)
            }
    }

    /**
     * Obtiene la clave remota para el último elemento en la lista.
     * @param state El estado de la paginación.
     * @return La clave remota para el último elemento o null si no se encuentra.
     */
    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, StoreEntity>
    ): StoreRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                storeDb.daoKeys.getRemoteKeys(id = unsplashImage.id)
            }
    }


}