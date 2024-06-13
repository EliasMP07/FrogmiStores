package com.example.frogmistores.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.frogmistores.data.local.dao.StoreRemoteKeyDao
import com.example.frogmistores.data.local.dao.StoresDao
import com.example.frogmistores.data.local.entities.StoreEntity
import com.example.frogmistores.data.local.entities.StoreRemoteKeys

/**
 * Base de datos Room que contiene las entidades StoreEntity y StoreRemoteKeys.
 * @param entities Lista de entidades incluidas en la base de datos.
 * @param version Versión de la base de datos.
 * @param exportSchema Indica si se debe exportar el esquema de la base de datos.
 */
@Database(
    entities =[StoreEntity::class, StoreRemoteKeys::class],
    version = 1,
    exportSchema = false,
)
abstract class FrogmiStoreDatabase: RoomDatabase(){
    /**
     * DAO para acceder a los datos de las tiendas.
     * @return Instancia de StoresDao.
     */
    abstract val dao: StoresDao

    /**
     * DAO para acceder a las claves remotas de las tiendas para la paginación.
     * @return Instancia de StoreRemoteKeyDao.
     */
    abstract val daoKeys: StoreRemoteKeyDao
}