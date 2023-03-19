package com.voidmemories.nifty50.domain

import com.voidmemories.nifty50.core.AppState
import com.voidmemories.nifty50.data.models.StockModel

interface Nifty50Repository {
    suspend fun getNifty50Details(): AppState<List<StockModel>>
}