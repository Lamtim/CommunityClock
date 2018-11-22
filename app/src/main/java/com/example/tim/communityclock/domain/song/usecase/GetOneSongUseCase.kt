package com.example.tim.communityclock.domain.song.usecase

import com.example.tim.communityclock.data.model.db.Song
import com.example.tim.communityclock.domain.song.repository.SongRepository
import com.imakeanapp.domain.core.ObservableUseCase

class GetOneSongUseCase (private val repository: SongRepository) : ObservableUseCase<Song> {

    override fun execute() = repository.getOneSong()

}