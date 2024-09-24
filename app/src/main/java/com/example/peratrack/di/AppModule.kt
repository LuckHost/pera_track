package com.example.peratrack.di

import android.content.Context
import com.peratrack.domain.UseCases.GetAllReceiptsUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideMainViewModelFactory(
        getAllReceiptsUseCase: GetAllReceiptsUseCase,
    ) : MainViewModelFactory {
        return MainViewModelFactory(
            getAllReceiptsUseCase = getAllReceiptsUseCase,
        )
    }
}