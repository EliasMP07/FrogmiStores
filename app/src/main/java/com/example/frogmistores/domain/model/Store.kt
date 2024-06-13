

package com.example.frogmistores.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * Clase de datos que representa una tienda.
 * Implementa Parcelable para permitir la transferencia de instancias entre componentes de Android.
 * También implementa Serializable para permitir la serialización/deserialización de JSON.
 *
 * @property id Identificador único de la tienda.
 * @property name Nombre de la tienda.
 * @property code Código de la tienda.
 * @property fullAddress Dirección completa de la tienda.
 * @property longitude Longitud geográfica de la tienda.
 * @property latitude Latitud geográfica de la tienda.
 */
@Parcelize
@Serializable
data class Store(
    val id: String = "",
    val name: String = "",
    val code: String = "",
    val fullAddress: String = "",
    val longitude: Double = 0.0,
    val latitude: Double =  0.0,
): Parcelable
