package com.example.frogmistores.domain

import androidx.paging.PagingData
import com.example.frogmistores.domain.model.Store
import com.example.frogmistores.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow

class GetAllStoresUseCase(
    private val repository: StoreRepository
) {
    operator fun invoke(): Flow<PagingData<Store>> = repository.getAllStores()
}