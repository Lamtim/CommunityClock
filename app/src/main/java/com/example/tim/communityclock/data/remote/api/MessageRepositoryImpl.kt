package com.example.tim.communityclock.data.remote.api

import android.util.Log
import com.example.tim.communityclock.data.model.api.Message
import com.example.tim.communityclock.domain.message.repository.MessageRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : MessageRepository {

    override fun sendMessage(message: Message): Completable {
        Log.e("sendMessage","$db")
        return Completable.create { emitter ->
            db.collection("messages")
                    .add(message)
                    .addOnSuccessListener {
                        Log.e("COMPLETED","COMPLETED")
                        emitter.onComplete()
                    }
                    .addOnFailureListener {
                        Log.e("FAILED","FAILED")
                        emitter.onError(it) }
                    .addOnCompleteListener {
                        Log.e("Completed","Completed")
                    }
                    .addOnCanceledListener {
                        Log.e("Canceled","Canceled")
                    }
        }.subscribeOn(Schedulers.io())
    }

    override fun getOneMessage(): Observable<Message> {
        return Observable.create(ObservableOnSubscribe<Message> { emitter ->
            db.collection("messages")
                    .orderBy("sent", Query.Direction.DESCENDING)
                    .addSnapshotListener { snapshots, e ->
                        if (e != null) {
                            emitter.onError(e)
                            return@addSnapshotListener
                        }

                        var message: Message = Message("","")
                        snapshots?.let {
                            for (doc in snapshots) {
                                message = Message(doc.getString("id")!!,doc.getString("content")!!)
                            }
                        }

                        emitter.onNext(message)
                    }
        }).subscribeOn(Schedulers.io())
    }

}
