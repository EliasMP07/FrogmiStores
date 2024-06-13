package com.example.frogmistores.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO que representa las coordenadas de una tienda.
 * @property latitude Latitud de la tienda.
 * @property longitude Longitud de la tienda.
 */
data class CoordinatesDto(
    @SerializedName("latitude")val latitude: Double,
    @SerializedName("longitude")val longitude: Double
)