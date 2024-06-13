package com.example.frogmistores.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO que representa los atributos de una tienda.
 * @property name Nombre de la tienda.
 * @property code Código de la tienda.
 * @property fullAddress Dirección completa de la tienda.
 * @property coordinates Coordenadas de la tienda.
 */
data class AttributesDto(
    @SerializedName("name") val name: String,
    @SerializedName("code") val code: String,
    @SerializedName("full_address") val fullAddress: String,
    @SerializedName("coordinates") val coordinates: CoordinatesDto
)