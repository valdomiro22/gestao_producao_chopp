package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.CardStatusProducao
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.SelecionarTurno
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.LocalNavController
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.Screen
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.Dimens

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
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.EspacamentoG),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Bloco do Produto
                Column {
                    Text(
                        text = "Produto",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "ITAIPAVA",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Bloco da Ordem
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Ordem",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "10678085",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary // Um toque de cor no número
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.EspacamentoG),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardStatusProducao(
                backGround = Color(0xFF2563EB),
                titulo = "Programado",
                quantidade = "398"
            )

            CardStatusProducao(
                backGround = Color(0xFF22C55E),
                titulo = "Produzido",
                quantidade = "250"
            )

            CardStatusProducao(
                backGround = Color(0xFFEF4444),
                titulo = "Pendente",
                quantidade = "120"
            )
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        SelecionarTurno()

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}