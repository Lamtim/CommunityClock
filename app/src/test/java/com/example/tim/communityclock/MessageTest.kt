package com.example.tim.communityclock

import com.example.tim.communityclock.data.remote.api.MessageRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.junit.Rule




@RunWith(MockitoJUnitRunner::class)
class MessageTest {

    @Mock
    lateinit var firestore: FirebaseFirestore

    @InjectMocks
    private lateinit var mockMessRepo: MessageRepositoryImpl

    @Test
    fun verifyInteractionTimes(){
        verify(mockMessRepo).getOneMessage()
    }
}