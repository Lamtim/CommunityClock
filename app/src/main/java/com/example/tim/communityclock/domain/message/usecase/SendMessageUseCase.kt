package com.example.tim.communityclock.domain.message.usecase

import com.example.tim.communityclock.domain.message.repository.MessageRepository
import com.imakeanapp.domain.core.CompletableWithParamUseCase

class SendMessageUseCase(private val repository: MessageRepository) : CompletableWithParamUseCase<String> {

    override fun execute(t: String) = repository.sendMessage(t)
}