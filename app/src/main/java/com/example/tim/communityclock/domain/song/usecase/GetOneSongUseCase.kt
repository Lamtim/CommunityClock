package com.example.tim.communityclock.domain.song.usecase

import com.example.tim.communityclock.domain.core.ObservableWithParamUseCase
import com.example.tim.communityclock.domain.song.repository.SongRepository

class GetOneSongUseCase (private val repository: SongRepository) : ObservableWithParamUseCase<String,String> {

    override fun execute(song: String) = repository.getOneSong(song)

}