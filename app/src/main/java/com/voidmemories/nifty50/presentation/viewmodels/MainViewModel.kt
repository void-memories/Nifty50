package com.voidmemories.nifty50.presentation.viewmodels

import android.os.Handler
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voidmemories.nifty50.core.AppState
import com.voidmemories.nifty50.data.models.StockModel
import com.voidmemories.nifty50.domain.Nifty50Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Nifty50Repository) : ViewModel() {
    private val updateHandler: Handler = Handler()
    private val _nifty50LiveData = MutableLiveData<AppState<List<StockModel>>>()

    val nifty50LiveData: LiveData<AppState<List<StockModel>>>
        get() = _nifty50LiveData

    val periodicFetcher = object : Runnable {
        override fun run() {
            fetchNifty50()
            updateHandler.postDelayed(this, 10000)
        }
    }

    @VisibleForTesting
    fun setNifty50LiveData(appState: AppState<List<StockModel>>) {
        _nifty50LiveData.postValue(appState)
    }

    fun fetchNifty50() = viewModelScope.launch(Dispatchers.IO) {
        _nifty50LiveData.postValue(repository.getNifty50Details())
    }
}
