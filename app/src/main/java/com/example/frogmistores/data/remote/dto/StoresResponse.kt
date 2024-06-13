package com.example.frogmistores.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO que representa la respuesta de la API que contiene una lista de tiendas.
 * @property stores Lista de tiendas.
 */
data class StoresResponse(
   @SerializedName("data") val stores: List<StoreDto>,
)