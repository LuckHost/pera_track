package com.example.peratrack.presentation

import androidx.lifecycle.ViewModel
import com.peratrack.domain.UseCases.GetAllReceiptsUseCase
import com.peratrack.domain.models.Receipt

class MainViewModel(
    val getAllReceiptsUseCase: GetAllReceiptsUseCase,
) : ViewModel() {

    val receipts = mutableListOf<Receipt>()

    fun getAllReceipts() {
        receipts.clear()
        receipts.addAll(getAllReceiptsUseCase.execute())
    }
}