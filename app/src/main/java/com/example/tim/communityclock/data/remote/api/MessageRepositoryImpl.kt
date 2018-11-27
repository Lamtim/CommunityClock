package com.example.tim.communityclock.data.remote.api

import android.util.Log
import com.example.tim.communityclock.data.model.api.Message
import com.example.tim.communityclock.domain.message.repository.MessageRepository
import com.example.tim.communityclock.utils.DateUtils
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : MessageRepository {

    lateinit var registration: ListenerRegistration

    override fun sendMessage(content: String): Completable {
        val randomDate = DateUtils.getRandomDate()
        val message = Message("message",content,randomDate.time)
        return Completable.create { emitter ->
            db.collection("messages")
                    .add(message)
                    .addOnSuccessListener {
                        emitter.onComplete()
                    }
                    .addOnFailureListener {
                        emitter.onError(it) }
        }.subscribeOn(Schedulers.io())
    }

    override fun getOneMessage(): Observable<Message> {
        val pickDate = DateUtils.getRandomDate()
        Log.e("PISCKDATE","${pickDate.time}")
        return Observable.create(ObservableOnSubscribe<Message> { emitter ->
            val ref: CollectionReference = db.collection("messages")
            ref.whereLessThan(Message.RANDOM,pickDate.time).orderBy(Message.RANDOM, Query.Direction.DESCENDING).limit(2)
            registration = ref.addSnapshotListener { snapshots, e ->
                        if (e != null) {
                            emitter.onError(e)
                            return@addSnapshotListener
                        }

                        var message = Message("1","", 0)
                        Log.e("length snapshot","${snapshots?.size()}")
                        snapshots?.let {
                            for (doc in snapshots) {
                                message = Message(doc!!.data[Message.ID].toString(),doc.data[Message.CONTENT].toString(), doc.data[Message.RANDOM]!!.toString().toLong())
                                Log.e("message","${message.random}")

                            }
                        }
                        emitter.onNext(message)
                    }
        }).subscribeOn(Schedulers.io())
    }

    fun cancelRegistration(){
        registration.remove()
    }

}
