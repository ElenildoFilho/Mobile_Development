package com.example.forumapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forumapp.data.RetrofitInstance
import com.example.forumapp.data.CreatePostRequest
import com.example.forumapp.data.Post
import com.example.forumapp.data.User
import com.example.forumapp.data.UserCreateRequest
import kotlinx.coroutines.launch


class PostViewModel: ViewModel() {

    var posts: List<Post> by mutableStateOf(listOf())
    var users: List<User> by mutableStateOf(listOf())

    init {
        fetchUsersAndPosts()
    }

    private fun fetchUsersAndPosts() {
        viewModelScope.launch {
            try {

                users = RetrofitInstance.api.getUsers()


                val allPosts = users.flatMap { user ->
                    RetrofitInstance.api.getPosts(user.id)
                }

                posts = allPosts
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                users = RetrofitInstance.api.getUsers()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {

                if (users.isEmpty()) {
                    fetchUsers()
                }


                val allPosts = users.flatMap { user ->
                    RetrofitInstance.api.getPosts(user.id)
                }

                posts = allPosts
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun createUser(name: String, email: String, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val newUser = UserCreateRequest(name, email)
                RetrofitInstance.api.createUser(newUser)
                fetchUsers()
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createPost(title: String, content: String, userId: Int, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val newPost = CreatePostRequest(title, content, userId)
                RetrofitInstance.api.createPost(userId, newPost)
                fetchPosts()
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deletePost(postId: Int) {
        viewModelScope.launch {
            try {
                println("Deleting post with ID: $postId")
                RetrofitInstance.api.deletePost(postId)
                fetchPosts()
            } catch (e: Exception) {
                e.printStackTrace()
                println("Error deleting post: ${e.message}")
            }
        }
    }


    fun updatePost(postId: Int, title: String, content: String) {
        viewModelScope.launch {
            try {
                val updatedPost = CreatePostRequest(title, content)
                RetrofitInstance.api.updatePost(postId, updatedPost)
                fetchPosts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}