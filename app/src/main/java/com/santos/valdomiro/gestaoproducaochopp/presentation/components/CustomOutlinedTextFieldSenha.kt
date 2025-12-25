package com.santos.valdomiro.gestaoproducaochopp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedTextFieldSenha(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    keyboardType: KeyboardType,
    mostrarSenha: Boolean,
    onvisibilidadeChange: () -> Unit,
    icone: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        maxLines = 1,
        onValueChange = onValueChange,
        placeholder = { Text(placeHolder) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (mostrarSenha) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = icone,
        trailingIcon = {
            IconButton(onClick = onvisibilidadeChange) {
                Icon(
                    imageVector = if (mostrarSenha) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (mostrarSenha) "Esconder senha" else "Mostrar senha"
                )
            }
        }
    )
}