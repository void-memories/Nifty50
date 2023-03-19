package com.voidmemories.nifty50.core

sealed class AppState<out T : Any> {
    class Loading(val message: String? = "Firing APIs, please wait...") : AppState<Nothing>()
    class Error(val message: String? = "We're having problems fetching data.") : AppState<Nothing>()
    class Success<out T : Any>(val data: T) : AppState<T>()
}