package com.luckhost.peratrack.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luckhost.peratrack.presentation.MainViewModel
import com.peratrack.domain.UseCases.GetAllReceiptsUseCase
import com.peratrack.domain.UseCases.SaveReceiptUseCase

class MainViewModelFactory(
    private val getAllReceiptsUseCase: GetAllReceiptsUseCase,
    private val saveReceiptUseCase: SaveReceiptUseCase,

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getAllReceiptsUseCase = getAllReceiptsUseCase,
            saveReceiptUseCase = saveReceiptUseCase
        ) as T
    }
}