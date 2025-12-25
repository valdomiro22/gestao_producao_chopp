package com.santos.valdomiro.gestaoproducaochopp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    keyboardType: KeyboardType,
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
        leadingIcon = icone
    )
}


// Como usar
//CustomOutlinedTextField(
//    value = nome,
//    onValueChange = {nome = it},
//    placeHolder = stringResource(R.string.placeholder_nome),
//    keyboardType = KeyboardType.Text
//)