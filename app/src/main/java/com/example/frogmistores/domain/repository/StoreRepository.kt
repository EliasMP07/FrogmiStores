package com.example.frogmistores.domain.repository

import androidx.paging.PagingData
import com.example.frogmistores.domain.model.Store
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    fun getAllStores(): Flow<PagingData<Store>>
}