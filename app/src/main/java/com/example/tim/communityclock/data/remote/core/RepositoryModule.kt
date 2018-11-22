package com.example.tim.communityclock.data.remote.core

import com.example.tim.communityclock.data.remote.api.SongRepositoryImpl
import com.example.tim.communityclock.domain.message.repository.MessagesRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesMessageRepository(repository: SongRepositoryImpl): SongRepositoryImpl {
        return repository
    }

    @Provides
    fun providesSongRepository(repository: MessagesRepository): MessagesRepository {
        return repository
    }
}