package com.example.forumapp.ui.theme.Screen

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.forumapp.ViewModel.PostViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.forumapp.data.Post

@Composable
fun PostScreen(viewModel: PostViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var editingPost by remember { mutableStateOf<Post?>(null) }
    val context = LocalContext.current
    var user by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        isLoading = true

        isLoading = false
    }

    Column(modifier = Modifier.padding(16.dp)) {


        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("User Id") },
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Conteudo") },
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("user") },
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {

                if (user.isEmpty()) {
                    Toast.makeText(context, "Please select a user", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (title.isEmpty()) {
                    Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (content.isEmpty()) {
                    Toast.makeText(context, "Please enter a content", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                isLoading = true
                viewModel.createPost(title, content, user.toInt(), onSuccess = {
                    Toast.makeText(context, "Post created successfully", Toast.LENGTH_SHORT).show()
                    isLoading = false
                },
                    onError = {
                        Toast.makeText(context, "Error creating post", Toast.LENGTH_SHORT).show()
                        isLoading = false
                    })

                title = ""
                content = ""
                user = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Create Post")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if(isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn {
                items(viewModel.posts) { post ->
                    PostCard(
                        post,
                        onDelete = {viewModel.deletePost( post.id )},
                        onEdit = {editingPost = post},
                    )
                }
            }
        }

        if (editingPost != null) {
            AlertDialog(
                onDismissRequest = { editingPost = null },
                title = { Text(text = "Editar Post") },
                text = {
                    Column {

                        OutlinedTextField(
                            value = editingPost!!.title,
                            onValueChange = { newTitle ->
                                editingPost = editingPost!!.copy(title = newTitle)
                            },
                            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.primary
                            ),
                            label = { Text("Título") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))


                        OutlinedTextField(
                            value = editingPost!!.content,
                            onValueChange = { newContent ->
                                editingPost = editingPost!!.copy(content = newContent)
                            },
                            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.primary
                            ),
                            label = { Text("Conteúdo") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            maxLines = 5,
                            singleLine = false
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.updatePost(
                            editingPost!!.id,
                            editingPost!!.title,
                            editingPost!!.content
                        )
                        editingPost = null
                    }) {
                        Text(text = "Salvar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        editingPost = null
                    }) {
                        Text(text = "Cancelar")
                    }
                }

            )
        }


    }
}
