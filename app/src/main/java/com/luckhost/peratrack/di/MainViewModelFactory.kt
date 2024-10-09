package com.luckhost.peratrack.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luckhost.peratrack.presentation.MainViewModel
import com.peratrack.domain.useCases.DeleteReceiptUseCase
import com.peratrack.domain.useCases.GetAllReceiptsUseCase
import com.peratrack.domain.useCases.SaveReceiptUseCase

class MainViewModelFactory(
    private val getAllReceiptsUseCase: GetAllReceiptsUseCase,
    private val saveReceiptUseCase: SaveReceiptUseCase,
    private val deleteReceiptUseCase: DeleteReceiptUseCase

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getAllReceiptsUseCase = getAllReceiptsUseCase,
            saveReceiptUseCase = saveReceiptUseCase,
            deleteReceiptUseCase = deleteReceiptUseCase
        ) as T
    }
}