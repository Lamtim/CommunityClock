package com.example.tim.communityclock.data.remote.api

import com.example.tim.communityclock.data.model.db.Song
import com.example.tim.communityclock.domain.song.repository.SongRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : SongRepository {

    override fun getOneSong(): Observable<Song> {
        return Observable.create(ObservableOnSubscribe<Song> { emitter ->
            db.collection("songs")
                    .orderBy("song", Query.Direction.DESCENDING)
                    .addSnapshotListener { snapshots, e ->
                        if (e != null) {
                            emitter.onError(e)
                            return@addSnapshotListener
                        }

                        var song: Song = Song("","")
                        snapshots?.let {
                            for (doc in snapshots) {
                                song = Song(doc.getString("id")!!,doc.getString("title")!!)
                            }
                        }

                        emitter.onNext(song)
                    }
        }).subscribeOn(Schedulers.io())
    }

}
