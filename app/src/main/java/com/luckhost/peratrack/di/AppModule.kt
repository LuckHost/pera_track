package com.luckhost.peratrack.di

import android.content.Context
import com.peratrack.domain.useCases.receiptsRepo.DeleteReceiptUseCase
import com.peratrack.domain.useCases.receiptsRepo.GetAllReceiptsUseCase
import com.peratrack.domain.useCases.receiptsRepo.SaveReceiptUseCase
import com.peratrack.domain.useCases.userParams.DeleteUserParamsUseCase
import com.peratrack.domain.useCases.userParams.SaveUserParamsUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule(
    private val context: Context
) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideMainViewModelFactory(
        getAllReceiptsUseCase: GetAllReceiptsUseCase,
        saveReceiptUseCase: SaveReceiptUseCase,
        deleteReceiptUseCase: DeleteReceiptUseCase,
        saveUserParamsUseCase: SaveUserParamsUseCase,
        deleteUserParamsUseCase: DeleteUserParamsUseCase
    ) : MainViewModelFactory {
        return MainViewModelFactory(
            getAllReceiptsUseCase = getAllReceiptsUseCase,
            saveReceiptUseCase = saveReceiptUseCase,
            deleteReceiptUseCase = deleteReceiptUseCase,
            saveUserParamsUseCase = saveUserParamsUseCase,
            deleteUserParamsUseCase = deleteUserParamsUseCase
        )
    }

    @Provides
    fun provideAuthViewModelFactory(
        saveUserParamsUseCase: SaveUserParamsUseCase,
        deleteUserParamsUseCase: DeleteUserParamsUseCase
    ) : AuthViewModelFactory {
        return AuthViewModelFactory(
            saveUserParamsUseCase = saveUserParamsUseCase,
            deleteUserParamsUseCase = deleteUserParamsUseCase
        )
    }
}