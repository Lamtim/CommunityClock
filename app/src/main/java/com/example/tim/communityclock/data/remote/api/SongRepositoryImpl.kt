package com.example.tim.communityclock.data.remote.api

import com.example.tim.communityclock.data.model.db.Song
import com.example.tim.communityclock.domain.song.repository.SongRepository
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject




class SongRepositoryImpl @Inject constructor(private val db: FirebaseStorage) : SongRepository {

    override fun getOneSong(): Observable<Song> {
        return Observable.create(ObservableOnSubscribe<Song> { emitter ->
            val rootRef = db.reference
            val imageUrlsRef = rootRef.child("songs")
            val randomNumber = Random().nextInt(imageUrlsRef.activeDownloadTasks.size)
            val snapshot: FileDownloadTask.TaskSnapshot? = imageUrlsRef.activeDownloadTasks.get(randomNumber).snapshot
            when(snapshot){
                null -> return@ObservableOnSubscribe
            }
            var song: Song = Song("","")
            emitter.onNext(song)
        }).subscribeOn(Schedulers.io())
    }

}
