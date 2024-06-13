@file:OptIn(ExperimentalPagingApi::class)

package com.example.frogmistores.data.paging

import android.net.http.HttpException
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

class FrogmiStoresRemoteMediator(
    private val storeDb: FrogmiStoreDatabase,
    private val frogmiStoreApi: FrogmiStoresApi
) : RemoteMediator<Int, StoreEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, StoreEntity>
    ): MediatorResult {
        return try {
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
            val response = frogmiStoreApi.getStores(
                pageCount = Const.ITEMS_PER_PAGE,
                page = currentPage
            ).stores.map {
                it.toStoreEntity()
            }
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1
            storeDb.withTransaction {
                if (loadType == LoadType.REFRESH){
                    storeDb.dao.clearAll()
                    storeDb.daoKeys.deleteAllRemoteKeys()
                }
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
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, StoreEntity>
    ): StoreRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                storeDb.daoKeys.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, StoreEntity>
    ): StoreRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                storeDb.daoKeys.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, StoreEntity>
    ): StoreRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                storeDb.daoKeys.getRemoteKeys(id = unsplashImage.id)
            }
    }


}