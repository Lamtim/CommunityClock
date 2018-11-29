package com.example.tim.communityclock.domain.message.usecase

import com.example.tim.communityclock.data.model.api.Message
import com.example.tim.communityclock.domain.message.repository.MessageRepository
import com.imakeanapp.domain.core.ObservableUseCase

class GetOneMessageUseCase(private val repository: MessageRepository) : ObservableUseCase<Message> {

    override fun execute() = repository.getOneMessage()

}