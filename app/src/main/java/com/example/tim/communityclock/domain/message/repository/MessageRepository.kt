package com.example.tim.communityclock.domain.message.repository


import com.example.tim.communityclock.data.model.api.Message
import io.reactivex.Observable


interface MessagesRepository {

    fun getOneMessage(): Observable<Message>

}