package com.example.a4chat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a4chat.model.Message
import kotlinx.coroutines.flow.Flow


@Dao
interface MessageDao {

    @Query("SELECT * FROM messages ORDER BY timeStamp ASC")
    fun getAllMessage() : Flow<List<Message>>

    @Insert
    suspend fun insertMessage(message: Message)

}