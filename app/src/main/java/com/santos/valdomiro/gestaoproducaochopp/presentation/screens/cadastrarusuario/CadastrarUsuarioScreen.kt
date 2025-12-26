package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.cadastrarusuario

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.santos.valdomiro.gestaoproducaochopp.R
import com.santos.valdomiro.gestaoproducaochopp.domain.model.Usuario
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.LocalNavController
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.Screen
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.Dimens
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.GestaoUsuarioFirebaseAuthTheme
import java.time.LocalDate

@Composable
fun CadastrarUsuarioScreen(
    modifier: Modifier = Modifier,
    viewModel: CadastrarUsuarioViewModel = hiltViewModel()
) {
    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }

    // Para mostrar/ocultar senhas
    var mostrarSenha by remember { mutableStateOf(false) }
    var mostrarConfirmarSenha by remember { mutableStateOf(false) }

    // Para ScrollView
    val scrollState = rememberScrollState()

    val context = LocalContext.current
    val navController = LocalNavController.current

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.sucesso, state.erro) {
        if (state.sucesso) {
            Toast.makeText(context, "Usuário cadastrado", Toast.LENGTH_SHORT).show()

            navController.navigate(Screen.Login.route) {
                // Remove a tela de cadastro da pilha para o usuário não voltar para ela
                popUpTo(Screen.Cadastro.route) { inclusive = true }
            }
        }

        if (state.erro != null) {
            Toast.makeText(context, state.erro, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(MeuEvento.LimparErro)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Habilita o scroll manual
            .imePadding() // Faz a tela "encolher" ou adicionar espaço quando o teclado sobe
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Cadastro",
            fontSize = 32.sp,
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Text(text = stringResource(R.string.label_nome), fontSize = 12.sp)
        OutlinedTextField(
            value = nome,
            maxLines = 1,
            onValueChange = { novoTexto -> nome = novoTexto },
            placeholder = { Text(stringResource(R.string.placeholder_nome)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Text(text = stringResource(R.string.label_sobrenome), fontSize = 12.sp)
        OutlinedTextField(
            value = sobrenome,
            maxLines = 1,
            onValueChange = { novoTexto -> sobrenome = novoTexto },
            placeholder = { Text(stringResource(R.string.placeholder_sobrenome)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Text(text = stringResource(R.string.label_email), fontSize = 12.sp)
        OutlinedTextField(
            value = email,
            maxLines = 1,
            onValueChange = { novoTexto -> email = novoTexto },
            placeholder = { Text(stringResource(R.string.placeholder_email)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Text(text = stringResource(R.string.laber_senha), fontSize = 12.sp)
        OutlinedTextField(
            value = senha,
            maxLines = 1,
            onValueChange = { novoTexto -> senha = novoTexto },
            placeholder = { Text(stringResource(R.string.placeholder_senha)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (mostrarSenha) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                IconButton(onClick = { mostrarSenha = !mostrarSenha }) {
                    Icon(
                        imageVector = if (mostrarSenha) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (mostrarSenha) "Esconder senha" else "Mostrar senha"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Text(text = stringResource(R.string.laber_confirmar_senha), fontSize = 12.sp)
        OutlinedTextField(
            value = confirmarSenha,
            maxLines = 1,
            onValueChange = { novoTexto -> confirmarSenha = novoTexto },
            placeholder = { Text(stringResource(R.string.placeholder_senha)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (mostrarConfirmarSenha) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                IconButton(onClick = { mostrarConfirmarSenha = !mostrarConfirmarSenha }) {
                    Icon(
                        imageVector = if (mostrarConfirmarSenha) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (mostrarConfirmarSenha) "Esconder senha" else "Mostrar senha"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Button(
            onClick = {
                if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
                    Toast.makeText(context, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (senha != confirmarSenha) {
                    Toast.makeText(context, "As senhas não conferem", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val novoUsuario = Usuario(
                    nome = nome,
                    sobrenome = sobrenome,
                    email = email,
                    idade = 19,
                    adicionadoEm = LocalDate.now()
                )

                viewModel.onEvent(
                    MeuEvento.Cadastrar(
                        email = email,
                        pass = senha,
                        usuario = novoUsuario
                    )
                )
            },
            enabled = !state.loading,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            if (state.loading) {
                Text("Cadastrando...")
            } else {
                Text("Cadastrar")
            }
        }

        if (state.loading) {
            Spacer(modifier = Modifier.height(Dimens.EspacamentoG))
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.info_ja_tem_conta))

            TextButton(
                onClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Cadastro.route) { inclusive = true }
                    }
                }
            ) {
                Text(text = stringResource(R.string.logar))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GestaoUsuarioFirebaseAuthTheme {
        CadastrarUsuarioScreen()
    }
}