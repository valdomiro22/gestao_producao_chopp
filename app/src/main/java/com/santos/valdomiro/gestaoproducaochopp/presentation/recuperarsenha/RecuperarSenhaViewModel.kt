package com.santos.valdomiro.gestaoproducaochopp.presentation.recuperarsenha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.RecuperarSenhaUseCase
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecuperarSenhaViewModel @Inject constructor(
    private val recuperarSenhaUseCase: RecuperarSenhaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Aguardando)
    val uiState = _uiState.asStateFlow()

    fun recuperarSenha(email: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            val result = recuperarSenhaUseCase(email)

            result.onSuccess { _uiState.value = UiState.Success(Unit) }
                .onFailure { _uiState.value = UiState.Error(it.message ?: "Erro desconhecido") }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Aguardando
    }
}