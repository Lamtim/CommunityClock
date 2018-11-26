package com.example.tim.communityclock.domain.message.repository


import com.example.tim.communityclock.data.model.api.Message
import io.reactivex.Completable
import io.reactivex.Observable


interface MessageRepository {

    fun getOneMessage(): Observable<Message>

    fun sendMessage(message: Message): Completable
}