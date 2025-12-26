package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.configuracoes

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaoproducaochopp.domain.model.Usuario
import com.santos.valdomiro.gestaoproducaochopp.domain.repository.AuthRepository
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.DeleteUserUseCase
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.GetCurrentUserUseCase
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.UpdateNomeUsuarioUseCase
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.UpdatePhotoProfileUseCase
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfiguracoesUsuarioViewModel @Inject constructor(
    private val updatePhotoProfileUseCase: UpdatePhotoProfileUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val authRepository: AuthRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateName: UpdateNomeUsuarioUseCase
) : ViewModel() {

    private val _atualizarFotoState = MutableStateFlow<UiState<Unit>>(UiState.Aguardando)
    val atualizarFotoState = _atualizarFotoState.asStateFlow()

    private val _deletarContaState = MutableStateFlow<UiState<Unit>>(UiState.Aguardando)
    val deletarContaState = _deletarContaState.asStateFlow()

    private val _recuperarUsuarioState = MutableStateFlow<UiState<Usuario>>(UiState.Aguardando)
    val recuperarUsuarioState = _recuperarUsuarioState.asStateFlow()

    private val _alterarNomeState = MutableStateFlow<UiState<Unit>>(UiState.Aguardando)
    val alterarNomeState = _alterarNomeState.asStateFlow()

    init {
        recuperarUsuario()
    }

    fun atualizarFoto(fileUri: Uri) {
        val uid = authRepository.getCurrentUserId()

        if (uid == null) {
            _atualizarFotoState.value = UiState.Error("Usuário não encontrado")
            return
        }

        viewModelScope.launch {
            _atualizarFotoState.value = UiState.Loading

            val result = updatePhotoProfileUseCase(uid, fileUri)

            result.onSuccess { _atualizarFotoState.value = UiState.Success(Unit) }
                .onFailure { _atualizarFotoState.value = UiState.Error(it.message ?: "Erro ao atualizar foto") }
        }
    }

    fun alterarNome(uid: String, novoNome: String, novoSobrenome: String) {
        if (uid.isBlank()) {
            _alterarNomeState.value = UiState.Error("Digite o ID")
            return
        } else if (novoNome.isBlank()) {
            _alterarNomeState.value = UiState.Error("Digite o nome")
            return
        } else if (novoSobrenome.isBlank()) {
            _alterarNomeState.value = UiState.Error("Digite o sobrenome")
            return
        }

        viewModelScope.launch {
            _alterarNomeState.value = UiState.Loading

            val result = updateName(uid = uid, novoNome = novoNome, novoSobrenome = novoSobrenome)
            result.onSuccess { _alterarNomeState.value = UiState.Success(Unit) }
                .onFailure { _alterarNomeState.value = UiState.Error(it.message ?: "Erro ao alterar nome de usuario") }
        }

    }

    fun deletarUsuario(email: String, password: String) {
        if (email.isBlank()) {
            _deletarContaState.value = UiState.Error("UID inválido")
            return
        } else if (password.isBlank()) {
            _deletarContaState.value = UiState.Error("Password inválido")
            return
        }

        viewModelScope.launch {
            _deletarContaState.value = UiState.Loading

            val result = deleteUserUseCase(email = email, password = password)

            result.onSuccess { _deletarContaState.value = UiState.Success(Unit) }
                .onFailure { _deletarContaState.value = UiState.Error(it.message ?: "Erro ao deletar usuario") }
        }
    }

    fun recuperarUsuario() {
        viewModelScope.launch {
            _recuperarUsuarioState.value = UiState.Loading
            val result = getCurrentUserUseCase()

            result.onSuccess { usuario ->
                if (usuario != null) {
                    _recuperarUsuarioState.value = UiState.Success<Usuario>(usuario)
                } else {
                    _recuperarUsuarioState.value = UiState.Error("Usuário não encontrado")
                }
            }.onFailure { error ->
                _recuperarUsuarioState.value = UiState.Error(error.message ?: "Erro ao recuperar usuario")
            }
        }
    }

    fun resetStates() {
        _atualizarFotoState.value = UiState.Aguardando
        _deletarContaState.value = UiState.Aguardando
    }
}