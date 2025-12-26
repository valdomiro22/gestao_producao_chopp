package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.LogarUsuarioUseCase
import com.santos.valdomiro.gestaoproducaochopp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.SincronizarEmailUseCase
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logarUsuarioUseCase: LogarUsuarioUseCase,
    private val sincronizarEmailUseCase: SincronizarEmailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Aguardando)
    val uiState = _uiState.asStateFlow()

    fun logar(email: String, senha: String) {
        if (email.isBlank()) {
            _uiState.value = UiState.Error("Digite seu e-mail")
            return
        }

        if (senha.isBlank()) {
            _uiState.value = UiState.Error("Digite a senha")
            return
        }

        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = logarUsuarioUseCase(email, senha)
            result
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                    sincronizarEmailUseCase()
                }
                .onFailure { exception ->
                    val erroMapeado = when {
                        exception.message?.contains("credential") == true -> "Email ou senha incorretos"
                        exception.message?.contains("network") == true -> "Sem conexão com a internet"
                        exception.message?.contains("too many requests") == true -> "Quantidade de tentativas excedido. Tente mais tarde"
                        else -> "Erro ao entrar. Verifique os campos"
                    }

                    _uiState.value = UiState.Error(erroMapeado)

                }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Aguardando
    }

}