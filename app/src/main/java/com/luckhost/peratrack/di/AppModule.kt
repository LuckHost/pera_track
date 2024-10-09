package com.luckhost.peratrack.di

import android.content.Context
import com.peratrack.domain.useCases.DeleteReceiptUseCase
import com.peratrack.domain.useCases.GetAllReceiptsUseCase
import com.peratrack.domain.useCases.SaveReceiptUseCase
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
        deleteReceiptUseCase: DeleteReceiptUseCase
    ) : MainViewModelFactory {
        return MainViewModelFactory(
            getAllReceiptsUseCase = getAllReceiptsUseCase,
            saveReceiptUseCase = saveReceiptUseCase,
            deleteReceiptUseCase = deleteReceiptUseCase
        )
    }
}