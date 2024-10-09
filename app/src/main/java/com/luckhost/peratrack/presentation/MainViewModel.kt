package com.luckhost.peratrack.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peratrack.domain.useCases.DeleteReceiptUseCase
import com.peratrack.domain.useCases.GetAllReceiptsUseCase
import com.peratrack.domain.useCases.SaveReceiptUseCase
import com.peratrack.domain.models.Receipt
import java.util.Date

class MainViewModel(
    val getAllReceiptsUseCase: GetAllReceiptsUseCase,
    val saveReceiptUseCase: SaveReceiptUseCase,
    val deleteReceiptUseCase: DeleteReceiptUseCase
) : ViewModel() {
    init {
        saveReceiptUseCase.execute(
            Receipt(
                0,
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

    fun clearList() {
        getAllReceiptsUseCase.execute().forEach {
            deleteReceiptUseCase.execute(it)
        }
    }
}
