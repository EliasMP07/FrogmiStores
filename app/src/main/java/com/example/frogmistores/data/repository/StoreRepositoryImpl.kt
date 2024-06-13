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
import com.example.frogmistores.domain.model.Store
import com.example.frogmistores.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreRepositoryImpl(
    private val frogmiStoresApi: FrogmiStoresApi,
    private val storeDatabase: FrogmiStoreDatabase
): StoreRepository {
    override fun getAllStores(): Flow<PagingData<Store>> {
        val pagingSourceFactory = {
            storeDatabase.dao.pagingSource()
        }
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = FrogmiStoresRemoteMediator(
                storeDb =  storeDatabase,
                frogmiStoreApi =  frogmiStoresApi
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map {pagingData ->
            pagingData.map {storeEntity ->
                storeEntity.toStore()
            }
        }
    }
}