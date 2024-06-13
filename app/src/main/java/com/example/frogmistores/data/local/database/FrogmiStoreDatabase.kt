package com.example.frogmistores.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.frogmistores.data.local.StoresDao
import com.example.frogmistores.data.local.entities.AttributesEntity
import com.example.frogmistores.data.local.entities.StoreEntity

@Database(
    entities =[StoreEntity::class, AttributesEntity::class],
    version = 1
)
abstract class FrogmiStoreDatabase: RoomDatabase(){
    abstract val dao: StoresDao
}