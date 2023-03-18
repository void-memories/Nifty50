package com.voidmemories.nifty50.data.models

data class StockModel(
    val symbol: String,
    val identifier: String,
    val lastPrice: Double,
    val change: Double,
    val pChange: Double,
)
