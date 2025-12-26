package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.alteraremail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.UpdateEmailUseCase
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlterarEmailViewModel @Inject constructor(
    private val updateEmailUseCase: UpdateEmailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Aguardando)
    val uiState = _uiState.asStateFlow()

    fun alterarEmail(novoEmail: String, senha: String) {
        if (novoEmail.isBlank()) {
            _uiState.value = UiState.Error("Email vazio")
            return
        }
        
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result =  updateEmailUseCase(newEmail = novoEmail, currentPassword = senha)
            result.onSuccess { _uiState.value = UiState.Success(Unit) }
                .onFailure { _uiState.value = UiState.Error(it.message ?: "Erro ao atualizar foto") }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Aguardando
    }

}