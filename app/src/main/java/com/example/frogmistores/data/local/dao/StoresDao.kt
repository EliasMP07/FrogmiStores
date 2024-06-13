package com.example.frogmistores.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.frogmistores.data.local.entities.StoreEntity

/**
 * DAO (Data Access Object) para acceder a los datos de las tiendas en la base de datos.
 * Proporciona métodos para insertar, obtener y eliminar datos de las tiendas.
 */
@Dao
interface StoresDao {
    /**
     * Inserta una lista de tiendas en la base de datos.
     * En caso de conflicto, reemplaza las tiendas existentes.
     * @param stores Lista de StoreEntity a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStores(stores: List<StoreEntity>)

    /**
     * Obtiene un PagingSource para la paginación de las tiendas.
     * Esto se usa para cargar datos de manera paginada en una lista.
     * @return Un PagingSource de tiendas.
     */
    @Query("SELECT * FROM Storeentity")
    fun pagingSource(): PagingSource<Int, StoreEntity>

    /**
     * Elimina todas las tiendas de la base de datos.
     */
    @Query("DELETE FROM StoreEntity")
    suspend fun clearAll()

}