package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.recuperarsenha

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.santos.valdomiro.gestaoproducaochopp.R
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.CustomOutlinedTextField
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.LocalNavController
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.Dimens

@Composable
fun RecuperarSenhaScreen(
    viewModel: RecuperarSenhaViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    val state by viewModel.uiState.collectAsState()
    val navController = LocalNavController.current

    LaunchedEffect(state) {
        when (state) {
            is UiState.Success -> { navController.popBackStack() }
            is UiState.Error -> {}
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.EspacamentoG),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFFE78080))
                .border(
                    width = 2.dp,
                    color = Color(0xFFE78080),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 15.dp, vertical = 5.dp)
        ) {
            Text(
                stringResource(R.string.recuperar_senha),
                fontSize = Dimens.TextoXG,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Icon(
            imageVector = Icons.Filled.LockReset,
            contentDescription = "Redefinir senha",
//            tint = Color(0xFFE21E27),
            tint = Color(0xFFE78080),
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Text(stringResource(R.string.guia_recuperar_senha))

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        CustomOutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeHolder = stringResource(R.string.placeholder_email),
            keyboardType = KeyboardType.Email,
            icone = { Icon(Icons.Default.Email, contentDescription = null) }
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Button(
            onClick = {
                viewModel.recuperarSenha(email)
                viewModel.resetState()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.enviar))
        }

    }

}