package com.example.frogmistores.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoordinatesDto(
    @SerializedName("latitude")val latitude: Double,
    @SerializedName("longitude")val longitude: Double
)