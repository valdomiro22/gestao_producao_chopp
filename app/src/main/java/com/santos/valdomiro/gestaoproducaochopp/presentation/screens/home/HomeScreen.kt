package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.LocalNavController
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.Screen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val navController = LocalNavController.current

    LaunchedEffect(state) {
        when (state) {
            is UiState.Success -> {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            }
            is UiState.Error -> {
                Toast.makeText(context, "Erro ao tentar Deslogar", Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(Modifier.height(50.dp))

        Button(
            onClick = { viewModel.deslogar() },
            contentPadding = PaddingValues(horizontal = 40.dp, vertical = 15.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                "Deslogar",
                fontSize = 18.sp
            )
        }

        Spacer(Modifier.height(100.dp))

        Button(
            onClick = {
                navController.navigate(Screen.Configuracoes.route)
            },
            contentPadding = PaddingValues(horizontal = 40.dp, vertical = 15.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                "Configurações",
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}