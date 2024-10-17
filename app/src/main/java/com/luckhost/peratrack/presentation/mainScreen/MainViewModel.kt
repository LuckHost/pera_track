package com.luckhost.peratrack.presentation.mainScreen


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peratrack.domain.useCases.receiptsRepo.DeleteReceiptUseCase
import com.peratrack.domain.useCases.receiptsRepo.GetAllReceiptsUseCase
import com.peratrack.domain.useCases.receiptsRepo.SaveReceiptUseCase
import com.peratrack.domain.models.Receipt
import com.peratrack.domain.models.UserParams
import com.peratrack.domain.useCases.userParams.DeleteUserParamsUseCase
import com.peratrack.domain.useCases.userParams.SaveUserParamsUseCase
import java.util.Date

class MainViewModel(
    val getAllReceiptsUseCase: GetAllReceiptsUseCase,
    val saveReceiptUseCase: SaveReceiptUseCase,
    val deleteReceiptUseCase: DeleteReceiptUseCase,
    val saveUserParamsUseCase: SaveUserParamsUseCase,
    val deleteUserParamsUseCase: DeleteUserParamsUseCase
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

    fun saveUserParams(login: String, password: String) {
        saveUserParamsUseCase.execute(
            UserParams(
                login,
                password
            )
        )
    }

    fun clearList() {
        getAllReceiptsUseCase.execute().forEach {
            deleteReceiptUseCase.execute(it)
        }
    }
}
