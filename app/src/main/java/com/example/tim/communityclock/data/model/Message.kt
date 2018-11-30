package com.example.tim.communityclock.data.model

class Message(val id: String, val content: String, val index: Int) {

    companion object {
        const val ID: String = "id"
        const val CONTENT: String = "content"
        const val INDEX: String = "index"
    }

}