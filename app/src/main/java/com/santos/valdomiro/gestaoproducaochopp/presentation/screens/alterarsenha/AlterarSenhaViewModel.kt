package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.alterarsenha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.UpdatePasswordUseCase
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlterarSenhaViewModel @Inject constructor(
    private val updatePasswordUseCase: UpdatePasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Aguardando)
    val uiState = _uiState.asStateFlow()

    fun alterarSenha(senhaAtual: String, novaSenha: String, confirmarNovaSenha: String) {
        if (senhaAtual.isBlank()) {
            _uiState.value = UiState.Error("Digite a senha atual")
            return
        } else if (novaSenha.isBlank()) {
            _uiState.value = UiState.Error("Digite a nova senha")
            return
        } else if (confirmarNovaSenha.isBlank()) {
            _uiState.value = UiState.Error("Confirme a nova senha")
            return
        } else if (novaSenha != confirmarNovaSenha) {
            _uiState.value = UiState.Error("Senhas não conferem")
            return
        }

        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = updatePasswordUseCase(currentPassword = senhaAtual, newPassword = novaSenha)
            result
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }
                .onFailure { _uiState.value = UiState.Error(it.message ?: "Erro ao alterar senha") }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Aguardando
    }

}