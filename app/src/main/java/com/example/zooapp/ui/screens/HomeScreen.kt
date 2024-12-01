package com.example.zooapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.zooapp.models.character
import com.example.zooapp.models.characterList
import com.example.zooapp.ui.components.characterListItem

@Composable
fun HomeScreen(onAnimalSelected: (character) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredCharacters = remember(searchQuery) {
        characterList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }
    Column {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Pesquisar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(filteredCharacters) { animal ->
                characterListItem(animal, onAnimalSelected)
            }
        }
    }
}

