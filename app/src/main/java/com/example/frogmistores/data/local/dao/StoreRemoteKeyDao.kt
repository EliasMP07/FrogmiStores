package com.example.frogmistores.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.frogmistores.data.local.entities.StoreRemoteKeys


@Dao
interface StoreRemoteKeyDao {

    @Query("SELECT * FROM storeremotekeys WHERE id=:id")
    suspend fun getRemoteKeys(id: String): StoreRemoteKeys

    @Insert
    suspend fun addAllRemoteKeys(remoteKeys: List<StoreRemoteKeys>)

    @Query("DELETE FROM storeremotekeys")
    suspend fun deleteAllRemoteKeys()

}