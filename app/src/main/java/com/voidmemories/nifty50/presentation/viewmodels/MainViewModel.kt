package com.voidmemories.nifty50.presentation.viewmodels

import android.os.Handler
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voidmemories.nifty50.core.Status
import com.voidmemories.nifty50.data.models.StockModel
import com.voidmemories.nifty50.domain.Nifty50Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Nifty50Repository) : ViewModel() {
    private val updateHandler: Handler = Handler()
    private val _nifty50LiveData = MutableLiveData<List<StockModel>?>()

    val nifty50LiveData: LiveData<List<StockModel>?>
        get() = _nifty50LiveData

    val runnableCode = object : Runnable {
        override fun run() {
            fetchNifty50()
            updateHandler.postDelayed(this, 10000)
        }
    }

    @VisibleForTesting
    fun setNifty50LiveData(newsList: List<StockModel>) {
        _nifty50LiveData.postValue(newsList)
    }

    fun fetchNifty50() = viewModelScope.launch(Dispatchers.IO) {
        val res = repository.getNifty50Details()
        if (res.status == Status.SUCCESS) {
            _nifty50LiveData.postValue(res.data)
        } else {
            _nifty50LiveData.postValue(null)
        }
    }

}
