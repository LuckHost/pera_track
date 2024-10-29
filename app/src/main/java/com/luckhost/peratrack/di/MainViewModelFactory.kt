package com.luckhost.peratrack.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luckhost.peratrack.presentation.mainScreen.MainViewModel
import com.peratrack.domain.useCases.receiptsRepo.DeleteReceiptUseCase
import com.peratrack.domain.useCases.receiptsRepo.GetAllReceiptsUseCase
import com.peratrack.domain.useCases.receiptsRepo.SaveReceiptUseCase
import com.peratrack.domain.useCases.userParams.DeleteUserParamsUseCase
import com.peratrack.domain.useCases.userParams.SaveUserParamsUseCase

class MainViewModelFactory(
    private val getAllReceiptsUseCase: GetAllReceiptsUseCase,
    private val saveReceiptUseCase: SaveReceiptUseCase,
    private val deleteReceiptUseCase: DeleteReceiptUseCase,
    private val saveUserParamsUseCase: SaveUserParamsUseCase,
    private val deleteUserParamsUseCase: DeleteUserParamsUseCase

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(
            getAllReceiptsUseCase = getAllReceiptsUseCase,
            saveReceiptUseCase = saveReceiptUseCase,
            deleteReceiptUseCase = deleteReceiptUseCase,
            saveUserParamsUseCase = saveUserParamsUseCase,
            deleteUserParamsUseCase = deleteUserParamsUseCase
        ) as T
    }
}