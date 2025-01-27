package com.example.a4chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.a4chat.data.local.database.AppDatabase
import com.example.a4chat.repository.MessageRepository
import com.example.a4chat.ui.theme.MsgAppTheme
import com.example.a4chat.ui.theme.view.MessageApp
import com.example.a4chat.viewmodel.MessageViewModel
import com.example.a4chat.viewmodel.MessageViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "message_database")
            .fallbackToDestructiveMigration().build()

        val repository = MessageRepository(db.messageDao())

        setContent {
            MsgAppTheme {
                val viewModel: MessageViewModel = viewModel(factory = MessageViewModelFactory(repository))
                MessageApp(viewModel)
            }
        }
    }
}