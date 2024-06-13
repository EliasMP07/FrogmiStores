package com.example.frogmistores.data.remote

import com.example.frogmistores.data.remote.dto.StoresResponse
import com.example.frogmistores.data.utils.Const
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FrogmiStoresApi {

    @Headers(
        "Authorization: Bearer ${Const.KEY}",
        "X-Company-UUID: ${Const.COMPANY_UUID}"
    )
    @GET("stores")
    suspend fun getStores(
        @Query("per_page") pageCount: Int,
        @Query("page") page: Int,
    ): StoresResponse


    companion object{
        const val BASE_URL = "https://api.frogmi.com/api/v3/"
    }
}