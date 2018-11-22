package com.example.tim.communityclock.domain.message.usecase

import com.example.tim.communityclock.data.model.api.Message
import com.example.tim.communityclock.domain.message.repository.MessagesRepository
import com.imakeanapp.domain.core.CompletableWithParamUseCase

class SendMessageUseCase (private val repository: MessagesRepository) : CompletableWithParamUseCase<Message> {

    override fun execute(t: Message) = repository.sendMessage(t)
}
