package com.example.frogmistores.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.frogmistores.data.local.entities.StoreEntity

@Dao
interface StoresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStores(stores: List<StoreEntity>)

    @Query("SELECT * FROM Storeentity")
    fun pagingSource(): PagingSource<Int, StoreEntity>

    @Query("DELETE FROM StoreEntity")
    suspend fun clearAll()

}