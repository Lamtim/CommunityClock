package com.example.tim.communityclock.data.repo

import android.util.Log
import com.example.tim.communityclock.BuildConfig
import com.example.tim.communityclock.data.model.Message
import com.example.tim.communityclock.domain.message.repository.MessageRepository
import com.example.tim.communityclock.utils.DateUtils
import com.google.firebase.firestore.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.text.DateFormat
import java.util.*

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : MessageRepository {

    private lateinit var registration: ListenerRegistration

    override fun sendMessage(content: String): Completable {
        val r = Random()
        val i = r.nextInt(1000)
        Log.e("INDEX","$i")
        val message = Message("message",content,i)
        return Completable.create { emitter ->
            db.collection(BuildConfig.FIREBASE_FIRESTORE_NAME)
                    .add(message)
                    .addOnSuccessListener {
                        emitter.onComplete()
                    }
                    .addOnFailureListener {
                        emitter.onError(it)
                    }
        }.subscribeOn(Schedulers.io())
    }

    override fun getOneMessage(): Observable<Message> {
        return Observable.create(ObservableOnSubscribe<Message> { emitter ->
            val ref: CollectionReference = db.collection(BuildConfig.FIREBASE_FIRESTORE_NAME)
            val r = Random()
            val i = r.nextInt(1000)
            Log.e("INDEX","$i")
            registration = ref
                    .whereLessThanOrEqualTo("index", i)
                    .orderBy("index",Query.Direction.DESCENDING)
                    .limit(1)
                    .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    emitter.onError(e)
                    return@addSnapshotListener
                }
                var message = Message("","", 0)
                Log.e("Size","${snapshots!!.size()}")
                snapshots?.let {
                    for (doc in snapshots) {
                        message = Message(doc.getString(Message.ID)!!,doc.getString(Message.CONTENT)!!, doc.getLong(Message.INDEX)!!.toInt())
                        Log.e("Content","${message.content}")
                        break
                    }
                }
                emitter.onNext(message)
            }
        }).subscribeOn(Schedulers.io())
    }

    fun cancelRegistration() {
        registration.remove()
    }

}
