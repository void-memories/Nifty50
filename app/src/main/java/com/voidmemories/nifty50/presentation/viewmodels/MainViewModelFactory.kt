package com.voidmemories.nifty50.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.voidmemories.nifty50.domain.Nifty50RepositoryImpl
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: Nifty50RepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}