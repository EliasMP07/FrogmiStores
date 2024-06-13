package com.example.frogmistores.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa las claves remotas para la paginación en la base de datos.
 * Cada propiedad de la entidad se mapea a una columna en la tabla correspondiente.
 */
@Entity
data class StoreRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
