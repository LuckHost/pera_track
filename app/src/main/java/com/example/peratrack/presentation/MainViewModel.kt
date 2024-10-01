package com.example.peratrack.presentation


import androidx.lifecycle.ViewModel
import com.peratrack.domain.UseCases.GetAllReceiptsUseCase



class MainViewModel(
    val getAllReceiptsUseCase: GetAllReceiptsUseCase,
) : ViewModel() {

}
