package com.example.frogmistores.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AttributesDto(
    @SerializedName("name") val name: String,
    @SerializedName("code") val code: String,
    @SerializedName("full_address") val fullAddress: String,
    @SerializedName("coordinates") val coordinates: CoordinatesDto
)