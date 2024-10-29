package com.luckhost.peratrack.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luckhost.peratrack.presentation.authScreen.AuthViewModel
import com.peratrack.domain.useCases.userParams.DeleteUserParamsUseCase
import com.peratrack.domain.useCases.userParams.SaveUserParamsUseCase

class AuthViewModelFactory(
    private val saveUserParamsUseCase: SaveUserParamsUseCase,
    private val deleteUserParamsUseCase: DeleteUserParamsUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(
            saveUserParamsUseCase = saveUserParamsUseCase,
            deleteUserParamsUseCase = deleteUserParamsUseCase
        ) as T
    }
}