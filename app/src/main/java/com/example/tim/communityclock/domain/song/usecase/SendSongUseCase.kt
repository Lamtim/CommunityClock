package com.example.tim.communityclock.domain.song.usecase

import com.example.tim.communityclock.domain.song.repository.SongRepository
import com.imakeanapp.domain.core.CompletableWithParamUseCase
import java.io.File

class SendSongUseCase (private val repository: SongRepository) : CompletableWithParamUseCase<File> {

    override fun execute(t: File) = repository.sendSong(t)

}