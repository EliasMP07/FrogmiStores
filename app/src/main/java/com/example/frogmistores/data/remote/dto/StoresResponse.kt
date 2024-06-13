package com.example.frogmistores.data.remote.dto

import com.squareup.moshi.Json

data class StoresResponse(
   @Json(name = "data") val stores: List<StoreDto>,
)