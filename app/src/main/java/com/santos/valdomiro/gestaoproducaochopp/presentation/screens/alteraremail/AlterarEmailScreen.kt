package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.alteraremail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.santos.valdomiro.gestaoproducaochopp.R
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.CustomOutlinedTextField
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.CustomOutlinedTextFieldSenha
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.LocalNavController
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.Dimens

@Composable
fun AlterarEmailScreen(
    viewModel: AlterarEmailViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val navController = LocalNavController.current
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state) {
        when (state) {
            is UiState.Success -> {
                navController.popBackStack()
            }
            is UiState.Error -> {

            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.EspacamentoG)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            stringResource(R.string.alterar_email),
            fontSize = Dimens.TextoXG,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Text(stringResource(R.string.guia_alterar_email))

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        CustomOutlinedTextField(
            value = email,
            onValueChange = {email = it},
            placeHolder = stringResource(R.string.placeholder_novo_email),
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        CustomOutlinedTextFieldSenha(
            value = senha,
            onValueChange = { senha = it },
            placeHolder = stringResource(R.string.placeholder_senha_atual),
            keyboardType = KeyboardType.Password,
            mostrarSenha = mostrarSenha,
            onvisibilidadeChange = { mostrarSenha = !mostrarSenha }
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Button(
            onClick = {
                viewModel.alterarEmail(email, senha)
                viewModel.resetState()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.atualizar))
        }

    }

}