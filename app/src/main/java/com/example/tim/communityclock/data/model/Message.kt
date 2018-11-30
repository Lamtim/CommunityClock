package com.example.tim.communityclock.data.model

class Message(val id: String, val content: String, val random: String) {

    companion object {
        const val ID: String = "id"
        const val CONTENT: String = "content"
        const val RANDOM: String = "random"
    }

}