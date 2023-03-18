package com.voidmemories.nifty50.data.datasources.api

import com.voidmemories.nifty50.core.Constants
import com.voidmemories.nifty50.data.models.StockModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface Nifty50Api {
    @Headers(
        "X-RapidAPI-Key:${Constants.API_KEY}",
        "X-RapidAPI-Host:${Constants.HOST}"
    )
    @GET("price?Indices=NIFTY%2050")
    suspend fun getNifty50Details(): Response<List<StockModel>>
}