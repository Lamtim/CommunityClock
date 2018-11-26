package com.example.tim.communityclock.data.model.api

class Message(val id: String, val content: String, val random: Long) {

    companion object {
        const val ID: String = "id"
        const val CONTENT: String = "content"
        const val RANDOM: String = "random"
    }

}