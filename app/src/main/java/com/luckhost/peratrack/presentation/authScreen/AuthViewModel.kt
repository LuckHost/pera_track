package com.luckhost.peratrack.presentation.authScreen

import androidx.lifecycle.ViewModel
import com.peratrack.domain.models.UserParams
import com.peratrack.domain.useCases.userParams.DeleteUserParamsUseCase
import com.peratrack.domain.useCases.userParams.SaveUserParamsUseCase

class AuthViewModel(
    val saveUserParamsUseCase: SaveUserParamsUseCase,
    val deleteUserParamsUseCase: DeleteUserParamsUseCase
) : ViewModel() {

    fun saveUserParams(login: String, password: String) {
        saveUserParamsUseCase.execute(
            UserParams(
                login,
                password
            )
        )
    }
}