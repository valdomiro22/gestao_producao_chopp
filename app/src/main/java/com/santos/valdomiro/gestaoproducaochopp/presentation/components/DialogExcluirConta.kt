package com.santos.valdomiro.gestaoproducaochopp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.santos.valdomiro.gestaoproducaochopp.presentation.configuracoes.ConfiguracoesUsuarioViewModel

@Composable
fun DialogExcluirConta(
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: ConfiguracoesUsuarioViewModel? = null
) {

    var senhaVisivel by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Confirme sua identidade",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(Modifier.height(10.dp))

            Text("Para escluir a conta, confirme sua identidade, digite o email e senha novamente",)

            Spacer(Modifier.height(8.dp))

            CustomOutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeHolder = "Email",
                keyboardType = KeyboardType.Email,
                icone = { Icon(Icons.Default.Email, contentDescription = null) }
            )

            Spacer(Modifier.height(8.dp))

            CustomOutlinedTextFieldSenha(
                value = senha,
                onValueChange = { senha = it },
                placeHolder = "Senha",
                keyboardType = KeyboardType.Password,
                mostrarSenha = senhaVisivel,
                onvisibilidadeChange = { senhaVisivel = !senhaVisivel },
                icone = { Icon(Icons.Default.Lock, contentDescription = null) }
            )

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = onCancel) {
                    Text("Cancelar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                TextButton(onClick = {
                    viewModel?.deletarUsuario(email = email, password = senha)
                    onConfirm()
                }) {
                    Text("Excluir")
                }
            }
        }
    }
}
