package com.example.frogmistores.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa una tienda en la base de datos.
 * Cada propiedad de la entidad se mapea a una columna en la tabla correspondiente.
 */
@Entity
data class StoreEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "full_address")
    val fullAddress: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "longitude")
    val longitude: Double,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
)