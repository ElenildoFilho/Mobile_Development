package com.example.forumapp.data

data class Post(
    val id: Int,
    val title: String,
    val content: String,
    val ownerId: Int
)

data class CreatePostRequest(
    val title: String,
    val content: String,
    val ownerId: Int? = null
)
