package com.example.tim.communityclock.data.remote.api

import android.net.Uri
import android.util.Log
import com.example.tim.communityclock.data.model.db.Song
import com.example.tim.communityclock.domain.song.repository.SongRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StreamDownloadTask
import com.google.firebase.storage.UploadTask
import io.reactivex.Completable
import io.reactivex.CompletableOnSubscribe
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.io.File
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(private val db: FirebaseStorage) : SongRepository {

    override fun sendSong(file: File): Completable {
        return Completable.create { emitter ->
            var uri = Uri.fromFile(file)
            val storageRef = db.reference
            val riversRef = storageRef.child("images/${file.name}")
            if (uri.path!!.isEmpty())
                return@create
            val uploadTask: UploadTask = riversRef.putFile(uri)
            uploadTask.addOnFailureListener {
                emitter.onError(it)
            }.addOnSuccessListener {
                emitter.onComplete()
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun getOneSong(song: String): Observable<String> {
        return Observable.create(ObservableOnSubscribe<String> { emitter ->
            val storageRef = db.reference
            val pathReference = storageRef.child("songs/$song")
            val localFile = File.createTempFile("song", "mp3")
            pathReference.getFile(localFile).addOnSuccessListener {
                emitter.onNext(localFile.absolutePath)
                emitter.onComplete()
            }.addOnFailureListener {
                emitter.onError(it)
            }
        }).subscribeOn(Schedulers.io())
    }


}
