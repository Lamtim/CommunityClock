package com.example.tim.communityclock.data.remote.core

import com.example.tim.communityclock.data.remote.api.SongRepositoryImpl
import com.example.tim.communityclock.domain.message.repository.MessageRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesMessageRepository(repository: SongRepositoryImpl): SongRepositoryImpl {
        return repository
    }

    @Provides
    fun providesSongRepository(repository: MessageRepository): MessageRepository {
        return repository
    }
}