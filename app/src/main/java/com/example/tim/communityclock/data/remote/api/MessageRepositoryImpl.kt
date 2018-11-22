package com.example.tim.communityclock.data.remote.api

import com.example.tim.communityclock.data.model.api.Message
import com.example.tim.communityclock.domain.message.repository.MessagesRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MessageRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : MessagesRepository {

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
