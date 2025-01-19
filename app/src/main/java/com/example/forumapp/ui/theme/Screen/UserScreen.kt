package com.example.forumapp.ui.theme.Screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.forumapp.ViewModel.PostViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserScreen(viewModel: PostViewModel = viewModel()) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var selectedUserId by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.fetchUsers()
        isLoading = false
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") },
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                isLoading = true
                viewModel.createUser(name, email, onSuccess = {
                    Toast.makeText(context, "User created successfully", Toast.LENGTH_SHORT).show()
                    isLoading = false
                }, onError = {
                    Toast.makeText(context, "Error creating user", Toast.LENGTH_SHORT).show()
                    isLoading = false
                })
                name = ""
                email = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Create User")
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn {
                items(viewModel.users) { user ->
                    UserCard(
                        user = user,
                        isSelected = user.id == selectedUserId, // Verifica se o card está selecionado
                        onClick = { selectedUserId = user.id }
                    )
                }
            }
        }
    }
}