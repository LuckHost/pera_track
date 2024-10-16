package com.luckhost.peratrack.di

import com.peratrack.domain.useCases.receiptsRepo.DeleteReceiptUseCase
import com.peratrack.domain.useCases.receiptsRepo.GetAllReceiptsUseCase
import com.peratrack.domain.useCases.receiptsRepo.SaveReceiptUseCase
import com.peratrack.domain.repositories.LocalReceiptsRepoInterface
import com.peratrack.domain.repositories.MailboxRepoInterface
import com.peratrack.domain.repositories.UserParamsRepoInterface
import com.peratrack.domain.useCases.userParams.DeleteUserParamsUseCase
import com.peratrack.domain.useCases.userParams.SaveUserParamsUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetAllReceiptsUseCase(
        localRepo: LocalReceiptsRepoInterface,
        mailRepo: MailboxRepoInterface,
        paramsRepo: UserParamsRepoInterface,
    ) : GetAllReceiptsUseCase {
        return GetAllReceiptsUseCase(
            localRepo,
            mailRepo,
            paramsRepo
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

    @Provides
    fun provideDeleteReceiptUseCase(
        localRepo: LocalReceiptsRepoInterface
    ) : DeleteReceiptUseCase {
        return DeleteReceiptUseCase(
            localRepo
        )
    }

    @Provides
    fun provideSaveUserParamsUseCase(
        userParamsRepo: UserParamsRepoInterface
    ) : SaveUserParamsUseCase {
        return SaveUserParamsUseCase(
            userParamsRepo
        )
    }

    @Provides
    fun provideDeleteUserParamsUseCase(
        userParamsRepo: UserParamsRepoInterface
    ) : DeleteUserParamsUseCase {
        return DeleteUserParamsUseCase(
            userParamsRepo
        )
    }
}