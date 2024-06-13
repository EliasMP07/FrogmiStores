package com.example.frogmistores.data.remote.dto

import com.google.gson.annotations.SerializedName


data class StoresResponse(
   @SerializedName("data") val stores: List<StoreDto>,
)