package com.example.a4chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a4chat.model.Message
import com.example.a4chat.repository.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MessageViewModel(private val repository: MessageRepository): ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())

    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allMessages.collect {_messages.value = it}
        }
    }

    fun addMessage(content: String) {
        viewModelScope.launch {
//            val newMessage = Message(content = content, timeStamp = System.currentTimeMillis())
//            _messages.value += newMessage // Atualiza a lista de mensagens
            repository.addMessage(content)
        }
    }

}