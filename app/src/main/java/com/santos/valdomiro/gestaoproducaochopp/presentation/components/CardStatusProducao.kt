package com.santos.valdomiro.gestaoproducaochopp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CardStatusProducao(
    backGround: Color,
    titulo: String,
    quantidade: String,
//    modifier: Modifier = Modifier, // Adicionado para permitir o weight
    largura: Dp = 110.dp,
    altura: Dp = 120.dp
) {
    val corComOpacidade = backGround.copy(alpha = 0.4f)

    Column(
        modifier = Modifier
            .width(largura)
            .height(altura)
//            .shadow(4.dp, RoundedCornerShape(10.dp)) // Adiciona profundidade
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, backGround, RoundedCornerShape(10.dp))
            .background(Color.White), // Fundo base para a sombra aparecer bem
    ) {
        // TÍTULO (Topo)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .background(backGround),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = titulo,
                color = Color.White,
                fontWeight = FontWeight.Bold, // Destaque no título
//                fontSize = 18.sp
            )
        }

        // QUANTIDADE (Base)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
                .background(corComOpacidade),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = quantidade,
                color = Color.Black,
                fontSize = 22.sp, // Número maior para facilitar leitura
                fontWeight = FontWeight.ExtraBold,
//                color = backGround // Cor forte no texto para contraste
            )
        }
    }
}