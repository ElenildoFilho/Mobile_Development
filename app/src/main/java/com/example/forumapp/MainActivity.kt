package com.example.forumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.forumapp.ui.theme.Screen.PostScreen
import com.example.forumapp.ui.theme.Screen.UserScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Forum App")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 4.dp
            )

        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.primary
            ) {

                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Usuários") },
                    label = { Text("Usuários") },
                    selected = navController.currentBackStackEntryAsState().value?.destination?.route == "users",
                    onClick = { navController.navigate("users") }
                )

                BottomNavigationItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Posts") },
                    label = { Text("Posts") },
                    selected = navController.currentBackStackEntryAsState().value?.destination?.route == "posts",
                    onClick = { navController.navigate("posts") }
                )
            }
        }

    ) { paddingValues ->

        // Navigation Host
        NavHost(navController = navController, startDestination = "users", modifier = Modifier.padding(paddingValues)) {
            composable("users") { UserScreen() }
            composable("posts") { PostScreen() }
        }
    }
}