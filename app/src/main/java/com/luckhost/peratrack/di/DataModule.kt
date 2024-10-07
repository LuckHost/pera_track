package com.luckhost.peratrack.di

import android.content.Context
import com.peratrack.data.LocalReceiptsRepoImpl
import com.peratrack.data.MailboxRepoImpl
import com.peratrack.domain.repositories.LocalReceiptsRepoInterface
import com.peratrack.domain.repositories.MailboxRepoInterface
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideMailboxRepoInterface() : MailboxRepoInterface {
        return MailboxRepoImpl()
    }

    @Provides
    fun provideLocalReceiptsRepoInterface(
        context: Context
    ) : LocalReceiptsRepoInterface {
        return LocalReceiptsRepoImpl(
            context = context
        )
    }
}