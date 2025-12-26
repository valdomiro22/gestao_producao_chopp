package com.santos.valdomiro.gestaoproducaochopp.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import com.santos.valdomiro.gestaoproducaochopp.R
import com.santos.valdomiro.gestaoproducaochopp.domain.model.Usuario
import com.santos.valdomiro.gestaoproducaochopp.presentation.screens.configuracoes.ConfiguracoesUsuarioViewModel
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.Dimens
import com.santos.valdomiro.gestaoproducaochopp.utils.TAG

@Composable
fun DialogEditarNome(
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: ConfiguracoesUsuarioViewModel? = null,
    usuario: Usuario?
) {

    var showDialog by remember { mutableStateOf(true) }
    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
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

            Text("Para escluir a conta, confirme sua identidade, digite o email e senha novamente")

            Spacer(Modifier.height(8.dp))

            CustomOutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                placeHolder = stringResource(R.string.label_nome),
                keyboardType = KeyboardType.Text,
                icone = {
                    Icon(Icons.Default.Person, contentDescription = null)
                }
            )

            Spacer(modifier = Modifier.height(Dimens.EspacamentoM))

            CustomOutlinedTextField(
                value = sobrenome,
                onValueChange = { sobrenome = it },
                placeHolder = stringResource(R.string.label_sobrenome),
                keyboardType = KeyboardType.Text,
                icone = {
                    Icon(Icons.Default.Person, contentDescription = null)
                }
            )

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = onCancel) {
                    Text("Cancelar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                TextButton(onClick = {
                    if (usuario != null) {
                        viewModel?.alterarNome(
                            uid = usuario.id,
                            novoNome = nome,
                            novoSobrenome = sobrenome
                        )
                        onConfirm()
                    } else {
                        Log.d(TAG, "DialogEditarNome: O usuario não existe")
                    }

                }) {
                    Text("Alterar")
                }
            }
        }
    }
}
