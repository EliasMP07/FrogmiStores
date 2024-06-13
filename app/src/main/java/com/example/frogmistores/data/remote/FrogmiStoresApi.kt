package com.example.frogmistores.data.remote

import com.example.frogmistores.data.remote.dto.StoresResponse
import com.example.frogmistores.data.utils.Const
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Interfaz que define las llamadas a la API de FrogmiStores.
 */
interface FrogmiStoresApi {

    /**
     * Obtiene una lista de tiendas desde la API.
     * @param pageCount Número de tiendas por página.
     * @param page Número de la página a obtener.
     * @return Respuesta que contiene la lista de tiendas.
     */
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