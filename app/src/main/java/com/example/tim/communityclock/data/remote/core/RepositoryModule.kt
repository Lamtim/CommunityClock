package com.example.tim.communityclock.data.remote.core

import com.example.tim.communityclock.data.remote.api.SongRepositoryImpl
import com.example.tim.communityclock.domain.message.repository.MessageRepository
import com.example.tim.communityclock.domain.song.repository.SongRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesMessageRepository(repository: SongRepositoryImpl): SongRepository {
        return repository
    }

    @Provides
    fun providesSongRepository(repository: MessageRepository): MessageRepository {
        return repository
    }
}