package com.luckhost.peratrack.di

import com.peratrack.data.LocalReceiptsRepoImpl
import com.peratrack.domain.UseCases.GetAllReceiptsUseCase
import com.peratrack.domain.UseCases.SaveReceiptUseCase
import com.peratrack.domain.repositories.LocalReceiptsRepoInterface
import com.peratrack.domain.repositories.MailboxRepoInterface
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetAllReceiptsUseCase(
        localRepo: LocalReceiptsRepoInterface,
        mailRepo: MailboxRepoInterface
    ) : GetAllReceiptsUseCase {
        return GetAllReceiptsUseCase(
            localRepo,
            mailRepo
        )
    }

    @Provides
    fun provideSaveReceiptUseCase(
        localRepo: LocalReceiptsRepoInterface
    ) : SaveReceiptUseCase {
        return SaveReceiptUseCase(
            localRepo
        )
    }
}