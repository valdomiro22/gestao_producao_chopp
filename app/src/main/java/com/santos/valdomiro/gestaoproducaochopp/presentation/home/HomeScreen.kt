package com.santos.valdomiro.gestaoproducaochopp.presentation.home

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState

@Composable
fun HomeScreen(
    irParaLogin: () -> Unit,
    irParaConfiguracoes: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state) {
        when (state) {
            is UiState.Success -> {
                irParaLogin()
                viewModel.resetState()
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
        Text("Home", fontSize = 32.sp)

        Spacer(Modifier.height(100.dp))

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
            onClick = { irParaConfiguracoes() },
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
        HomeScreen({}, {})
    }
}