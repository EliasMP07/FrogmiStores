package com.example.frogmistores.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO que representa una tienda.
 * @property id Identificador único de la tienda.
 * @property attributesDto Atributos de la tienda.
 */
data class StoreDto(
    @SerializedName("id") val id: String,
    @SerializedName("attributes") val attributesDto: AttributesDto
)