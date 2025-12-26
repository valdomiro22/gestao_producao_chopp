package com.santos.valdomiro.gestaoproducaochopp.presentation.screens.cadastrarusuario

import com.santos.valdomiro.gestaoproducaochopp.domain.model.Usuario

data class CadastrarUsuarioState(
    val loading: Boolean = false,
    val sucesso: Boolean = false,
    val erro: String? = null
)

sealed class MeuEvento {
    data class Cadastrar(
        val email: String,
        val pass: String,
        val usuario: Usuario
    ) : MeuEvento()

    data object LimparErro : MeuEvento()
}