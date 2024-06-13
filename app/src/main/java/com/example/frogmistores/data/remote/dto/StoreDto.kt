package com.example.frogmistores.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StoreDto(
    @SerializedName("id") val id: String,
    @SerializedName("attributes") val attributesDto: AttributesDto
)