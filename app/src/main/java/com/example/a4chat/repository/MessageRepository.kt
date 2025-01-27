package com.example.a4chat.repository

import com.example.a4chat.data.local.dao.MessageDao
import com.example.a4chat.model.Message
import kotlinx.coroutines.flow.Flow


class MessageRepository(private val dao: MessageDao) {

    val allMessages: Flow<List<Message>> = dao.getAllMessage()

    suspend fun addMessage(content: String) {
        val message = Message(content = content, timeStamp = System.currentTimeMillis())

        dao.insertMessage(message)
    }
}