package com.example.frogmistores.data.remote.dto

import com.squareup.moshi.Json

data class StoreDto(
    @Json(name = "attributes")val attributes: Attributes,
    @Json(name = "id")val id: String
)