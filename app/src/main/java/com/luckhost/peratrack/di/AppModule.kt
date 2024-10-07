package com.luckhost.peratrack.di

import android.content.Context
import com.peratrack.domain.UseCases.GetAllReceiptsUseCase
import com.peratrack.domain.UseCases.SaveReceiptUseCase
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
        saveReceiptUseCase: SaveReceiptUseCase
    ) : MainViewModelFactory {
        return MainViewModelFactory(
            getAllReceiptsUseCase = getAllReceiptsUseCase,
            saveReceiptUseCase = saveReceiptUseCase
        )
    }
}