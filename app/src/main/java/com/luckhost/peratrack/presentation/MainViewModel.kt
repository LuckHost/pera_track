package com.luckhost.peratrack.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peratrack.domain.UseCases.GetAllReceiptsUseCase
import com.peratrack.domain.UseCases.SaveReceiptUseCase
import com.peratrack.domain.models.Receipt
import java.util.Date

class MainViewModel(
    val getAllReceiptsUseCase: GetAllReceiptsUseCase,
    val saveReceiptUseCase: SaveReceiptUseCase
) : ViewModel() {
    init {
        saveReceiptUseCase.execute(
            Receipt(
                Date(),
                "",
                0f
            )
        )
    }

    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun updateCurrentReceipt() {
        currentName.value = getAllReceiptsUseCase.execute().toString()
    }


}
