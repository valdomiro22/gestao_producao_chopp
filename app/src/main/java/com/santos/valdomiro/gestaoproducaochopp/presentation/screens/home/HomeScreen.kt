package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.CardStatusProducao
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.LinhaChaveValor
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.SelecionarTurno
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.LocalNavController
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.Screen
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.Dimens
import com.santos.valdomiro.gestaoproducaochopp.utils.Turno

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    val turnoAtual by viewModel.turnoSelecionado.collectAsState()

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
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.EspacamentoG),
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(),
//                .padding(Dimens.EspacamentoG),
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
                .fillMaxWidth(),
//                .padding(horizontal = Dimens.EspacamentoG),
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

        // Seleção de turno e horarios

        // ... dentro da sua Column no HomeScreen

// 1. Título da Seção
        Text(
            text = "HORÁRIOS DO TURNO",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

// 2. Seletor de Turno em Estilo "Chips"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Turno.entries.forEach { turno ->
                val selecionado = turno == turnoAtual
                val corBase = if (selecionado) Color(0xFF2563EB) else Color(0xFFF0F0F0)

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(20.dp)) // Formato de cápsula
                        .background(corBase)
                        .clickable { viewModel.alterarTurno(turno) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = turno.label,
                        color = if (selecionado) Color.White else Color.DarkGray,
                        fontWeight = if (selecionado) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

// 3. Grid de Horários mais "Clean"
        val listaDeHorarios = turnoAtual.horarios.values.toList()

        LazyVerticalGrid(
            columns = GridCells.Fixed(4), // 4 colunas costumam ler melhor que 5 em telas menores
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp), // heightIn é melhor que height fixo
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(listaDeHorarios) { horario ->
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF8F9FA))
                        .border(1.dp, Color(0xFFE9ECEF), RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = horario,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF495057)
                    )
                    Text(
                        text = "100 b", // Unidade ou quantidade sutil
                        fontSize = 11.sp,
                        color = Color(0xFF0ED0A3),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        // Informações para o final de produção
        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Borda única e fina para o container
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Título do Card
            Text(
                text = "MONITORAMENTO DE VOLUME",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                letterSpacing = 1.sp
            )

            // Itens de Informação (Limpamos os modificadores internos)
            LinhaChaveValor(
                chave = "Volume do Barril",
                valor = "50 L",
                modifier = Modifier.fillMaxWidth()
            )

            // Um divisor sutil entre as informações
            HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFF0F0F0))

            LinhaChaveValor(
                chave = "Volume necessário",
                valor = "458 hl",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Alerta de Buffer (Flat, sem borda agressiva)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFEBEE), RoundedCornerShape(8.dp))
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "VERIFIQUE O BUFFER",
                    color = Color(0xFFB71C1C),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}