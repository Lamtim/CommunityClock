package com.example.tim.communityclock.domain.song.repository

import io.reactivex.Completable
import io.reactivex.Observable
import java.io.File

interface SongRepository {

    fun getOneSong(song: String): Observable<String>

    fun sendSong(file: File): Completable

}
