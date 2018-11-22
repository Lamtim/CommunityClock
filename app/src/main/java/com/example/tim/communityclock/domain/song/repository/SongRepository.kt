package com.example.tim.communityclock.domain.song.repository

import com.example.tim.communityclock.data.model.db.Song
import io.reactivex.Observable

interface SongRepository {

    fun getOneSong(): Observable<Song>

}
