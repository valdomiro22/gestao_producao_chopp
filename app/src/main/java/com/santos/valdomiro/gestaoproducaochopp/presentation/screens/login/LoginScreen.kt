package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.santos.valdomiro.gestaoproducaochopp.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.CustomOutlinedTextField
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.CustomOutlinedTextFieldSenha
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.CustomTextErro
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.LocalNavController
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.Screen
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }

    val state by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val navController = LocalNavController.current

    val isFormValid = email.isNotBlank() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            senha.isNotBlank() &&
            senha.length >= 6

    LaunchedEffect(state) {
        when (state) {
            is UiState.Success -> {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
            is UiState.Error -> {}
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.EspacamentoG),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.login),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(top = 50.dp, bottom = Dimens.EspacamentoXG)
                .align(Alignment.Start)
        )

        CustomOutlinedTextField(
            value = email,
            onValueChange = {email = it},
            placeHolder = stringResource(R.string.placeholder_email),
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        CustomOutlinedTextFieldSenha(
            value = senha,
            onValueChange = {senha = it},
            placeHolder = stringResource(R.string.placeholder_senha),
            keyboardType = KeyboardType.Password,
            mostrarSenha = mostrarSenha,
            onvisibilidadeChange = { mostrarSenha = !mostrarSenha }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Exibir mensangens de erro
            CustomTextErro((state as? UiState.Error)?.message ?: "")

            Text(
                text = "Esqueceu a senha?",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        navController.navigate(Screen.RecuperarSenha.route)
                    }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.logar(email, senha)
                viewModel.resetState()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Logar")
        }

//        if (state == UiState.Loading) {
//            Spacer(modifier = Modifier.height(Dimens.EspacamentoG))
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
//        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.info_ja_tem_conta))

            TextButton(
                onClick = {
                    navController.navigate(Screen.Cadastro.route)
                }
            ) {
                Text(text = stringResource(R.string.cadastrar))
            }
        }
    }
}