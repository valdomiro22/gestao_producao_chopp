package com.santos.valdomiro.gestaoproducaochopp.presentation.cadastrarusuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.CadastrarUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CadastrarUsuarioViewModel @Inject constructor(
    private val cadastrarUsuarioUseCase: CadastrarUsuarioUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CadastrarUsuarioState())
    val uiState =  _uiState.asStateFlow()

    fun onEvent(event: MeuEvento) {
        when (event) {
            is MeuEvento.Cadastrar -> {cadastrar(event)}
            is MeuEvento.LimparErro -> {_uiState.update { it.copy(erro = null) }}
        }
    }

    fun cadastrar(dados: MeuEvento.Cadastrar ) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, erro = null, sucesso = false) }

            try {
                cadastrarUsuarioUseCase(
                    email = dados.email,
                    password = dados.pass,
                    usuario = dados.usuario
                )
                _uiState.update { it.copy(loading = false, sucesso = true) }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        erro = e.message ?: "Erro desconhecido ao cadastrar"
                    )
                }
            }
        }
    }
}