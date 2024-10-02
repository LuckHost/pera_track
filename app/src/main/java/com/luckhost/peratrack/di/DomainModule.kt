package com.luckhost.peratrack.di

import com.peratrack.domain.UseCases.GetAllReceiptsUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetAllReceiptsUseCase() : GetAllReceiptsUseCase {
        return GetAllReceiptsUseCase()
    }
}