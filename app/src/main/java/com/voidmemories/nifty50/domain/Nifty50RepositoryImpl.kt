package com.voidmemories.nifty50.domain

import com.voidmemories.nifty50.core.AppState
import com.voidmemories.nifty50.data.datasources.api.Nifty50Api
import com.voidmemories.nifty50.data.models.StockModel
import javax.inject.Inject

class Nifty50RepositoryImpl @Inject constructor(private val nifty50Api: Nifty50Api) :
    Nifty50Repository {
    override suspend fun getNifty50Details(): AppState<List<StockModel>> {
        return try {
            val res = nifty50Api.getNifty50Details()

            if (res.isSuccessful && res.body() != null) {
                AppState.Success(res.body()!!)
            } else {
                AppState.Error()
            }
        } catch (e: java.lang.Exception) {
            AppState.Error()
        }
    }
}