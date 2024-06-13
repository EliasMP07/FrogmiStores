package com.example.frogmistores.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.frogmistores.data.local.entities.StoreRemoteKeys

/**
 * DAO (Data Access Object) para acceder a los datos de las claves remotas en la base de datos.
 * Proporciona métodos para insertar, obtener y eliminar claves remotas.
 */
@Dao
interface StoreRemoteKeyDao {

    /**
     * Obtiene las claves remotas para una tienda específica.
     * @param id ID de la tienda en formato String.
     * @return La entidad StoreRemoteKeys correspondiente al ID proporcionado.
     */
    @Query("SELECT * FROM storeremotekeys WHERE id=:id")
    suspend fun getRemoteKeys(id: String): StoreRemoteKeys

    /**
     * Inserta una lista de claves remotas en la base de datos.
     * @param remoteKeys Lista de StoreRemoteKeys a insertar.
     */
    @Insert
    suspend fun addAllRemoteKeys(remoteKeys: List<StoreRemoteKeys>)
    /**
     * Elimina todas las claves remotas de la base de datos.
     */
    @Query("DELETE FROM storeremotekeys")
    suspend fun deleteAllRemoteKeys()

}