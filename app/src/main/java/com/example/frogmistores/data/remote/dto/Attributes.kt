package com.example.frogmistores.data.remote.dto

import com.squareup.moshi.Json

data class Attributes(
    @Json(name = "code")val code: String,
    @Json(name = "full_address")val full_address: String,
    @Json(name = "name")val name: String
)