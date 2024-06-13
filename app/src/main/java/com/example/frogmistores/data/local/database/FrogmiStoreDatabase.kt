package com.example.frogmistores.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.frogmistores.data.local.StoresDao
import com.example.frogmistores.data.local.ddao.StoreRemoteKeyDao
import com.example.frogmistores.data.local.entities.StoreEntity
import com.example.frogmistores.data.local.entities.StoreRemoteKeys

@Database(
    entities =[StoreEntity::class, StoreRemoteKeys::class],
    version = 1,
    exportSchema = false,
)
abstract class FrogmiStoreDatabase: RoomDatabase(){
    abstract val dao: StoresDao
    abstract val daoKeys: StoreRemoteKeyDao
}