package com.example.peratrack.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.peratrack.presentation.MainViewModel
import com.peratrack.domain.UseCases.GetAllReceiptsUseCase

class MainViewModelFactory(
    private val getAllReceiptsUseCase: GetAllReceiptsUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getAllReceiptsUseCase = getAllReceiptsUseCase,
        ) as T
    }
}