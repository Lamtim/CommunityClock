package com.example.tim.communityclock.domain.message.usecase

import com.example.tim.communityclock.data.model.api.Message
import com.example.tim.communityclock.domain.message.repository.MessagesRepository
import com.imakeanapp.domain.core.ObservableUseCase

class GetOneMessageUseCase (private val repository: MessagesRepository) : ObservableUseCase<Message> {

    override fun execute() = repository.getOneMessage()

}