package com.example.roomdatabase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UserApp(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserApp(viewModel: UserViewModel) {
    val usuarios by viewModel.users.observeAsState(emptyList())
    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var usuarioEditando by remember { mutableStateOf<User?>(null) }
    var mensagemErro by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gerenciamento de Usuários") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = if (usuarioEditando == null) "Adicionar um novo Usuário" else "Editar Usuário",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text(text = "Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text(text = "Idade") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    try {
                        if (nome.isNotEmpty() && idade.isNotEmpty()) {
                            val idadeInt = idade.toInt()
                            if (usuarioEditando == null) {
                                viewModel.addUser(User(name = nome, age = idadeInt))
                            } else {
                                viewModel.updateUser(usuarioEditando!!.copy(name = nome, age = idadeInt))
                                usuarioEditando = null
                            }
                            nome = ""
                            idade = ""
                            mensagemErro = null
                        } else {
                            mensagemErro = "Preencha todos os campos"
                        }
                    } catch (e: NumberFormatException) {
                        mensagemErro = "Idade inválida"
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(if (usuarioEditando == null) "Adicionar usuário" else "Salvar alterações")
            }

            mensagemErro?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(it, color = Color.Red, modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Lista de Usuários",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            usuarios.forEach { user ->
                Text(
                    text = "${user.name}, ${user.age} anos",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}