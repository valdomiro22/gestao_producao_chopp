package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.configuracoes

import android.Manifest
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.FileProvider
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.santos.valdomiro.gestaoproducaochopp.R
import com.santos.valdomiro.gestaoproducaochopp.domain.model.Usuario
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.CampoImagemAlteravel
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.DialogEditarNome
import com.santos.valdomiro.gestaoproducaochopp.presentation.components.DialogExcluirConta
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.LocalNavController
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.Screen
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.Dimens
import com.santos.valdomiro.gestaoproducaochopp.utils.TAG
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ConfiguracoesUsuarioScreen(
    viewModel: ConfiguracoesUsuarioViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var showDialogExcluirConta by remember { mutableStateOf(false) }
    var showDialogEditarNome by remember { mutableStateOf(false) }
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)

    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val atualizarFotoState by viewModel.atualizarFotoState.collectAsState()
    val deletarContaState by viewModel.deletarContaState.collectAsState()
    val recuperarUsuarioState by viewModel.recuperarUsuarioState.collectAsState()
    val atualizarNomeState by viewModel.alterarNomeState.collectAsState()
    
    val navController = LocalNavController.current

    val usuario = (recuperarUsuarioState as? UiState.Success<Usuario>)?.data

    LaunchedEffect(atualizarFotoState) {
        when (atualizarFotoState) {
            is UiState.Success -> {
                Toast.makeText(context, "Foto atualizada", Toast.LENGTH_SHORT).show()
                viewModel.recuperarUsuario()
            }
            is UiState.Error -> {}
            else -> {}
        }
    }

    LaunchedEffect(deletarContaState) {
        when (deletarContaState) {
            is UiState.Success -> {
                navController.navigate(Screen.Login.route) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            }
            is UiState.Error -> {}
            else -> {}
        }
    }

    LaunchedEffect(atualizarNomeState) {
        when (atualizarNomeState) {
            is UiState.Success -> {
                Toast.makeText(context, "Nome alterado", Toast.LENGTH_SHORT).show()
                viewModel.recuperarUsuario()
            }
            is UiState.Error -> {}
            else -> {}
        }
    }

    val takePictureLaucher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            Log.d(TAG, "Foto tirada com sucesso! URI: $photoUri")
            viewModel.atualizarFoto(photoUri!!)
        } else {
            Log.d(TAG, "Foto cancelada ou falhou")
        }
    }


    fun createImageUri(): Uri {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFile = File(context.filesDir, "images/JPEG_${timestamp}.jpg")
        imageFile.parentFile?.mkdirs()

        return FileProvider.getUriForFile(
            context,
            "com.santos.valdomiro.gestaousuariofirebaseauth.provider",
//            "${context.packageName}.provider",
            imageFile
        )
    }

    Column(
        modifier = Modifier
            .padding(Dimens.EspacamentoG)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        CampoImagemAlteravel(
            photoUrl = usuario?.fotoUrl,
            onClick = {
                if (cameraPermission.status.isGranted) {
                    val uri = createImageUri()
                    photoUri = uri
                    takePictureLaucher.launch(uri)
                } else {
                    cameraPermission.launchPermissionRequest()
                }
            }
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        if (atualizarFotoState == UiState.Loading) {
            Spacer(modifier = Modifier.height(Dimens.EspacamentoG))
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "${usuario?.nome} ${usuario?.sobrenome}",
                fontSize = Dimens.TextoG,
            )
            IconButton(
                onClick = { showDialogEditarNome = true }
            ) {
                Icon(Icons.Default.Edit, "Editar nome")
            }
            if (showDialogEditarNome) {
                Dialog(onDismissRequest = { showDialogEditarNome = false }) {
                    DialogEditarNome(
                        onCancel = { showDialogEditarNome = false },
                        onConfirm = { showDialogEditarNome = false },
                        viewModel = viewModel,
                        usuario = usuario
                    )
                }
            }
        }

        Text(
            usuario?.email ?: "",
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        if (atualizarNomeState == UiState.Loading) {
            Spacer(modifier = Modifier.height(Dimens.EspacamentoG))
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Button(
            onClick = {
                navController.navigate(Screen.AlterarEmail.route)
            },
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(stringResource(R.string.alterar_email))
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Button(
            onClick = {
                navController.navigate(Screen.AlterarSenha.route)
            },
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(stringResource(R.string.alterar_senha))
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Button(
            onClick = { showDialogExcluirConta = true },
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text("Excluir Conta")
        }
        if (showDialogExcluirConta) {
            Dialog(onDismissRequest = { showDialogExcluirConta = false }) {
                DialogExcluirConta(
                    onCancel = { showDialogExcluirConta = false },
                    onConfirm = { showDialogExcluirConta = false },
                    viewModel = viewModel
                )
            }
        }

        if (deletarContaState == UiState.Loading) {
            Spacer(modifier = Modifier.height(Dimens.EspacamentoG))
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
