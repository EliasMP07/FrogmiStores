package com.example.frogmistores.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.frogmistores.data.local.entities.StoreEntity

@Dao
interface StoresDao {

    @Upsert
    suspend fun upsertAll(store: List<StoreEntity>)

    @Query("SELECT * FROM Storeentity")
    fun pagingSource(): PagingSource<Int, StoreEntity>

    @Query("DELETE FROM StoreEntity")
    suspend fun clearAll()

}