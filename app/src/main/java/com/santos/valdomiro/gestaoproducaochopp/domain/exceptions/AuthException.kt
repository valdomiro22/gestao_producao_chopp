package com.santos.valdomiro.gestaoproducaochopp.domain.exceptions

sealed class AuthException : Exception() {
    object EmailEmUso : AuthException()
    object SenhaFraca : AuthException()
    object EmailInvalido : AuthException()
    object ErroDeRede : AuthException()
    object CredenciaisInvalidas : AuthException()
    object UsuarioNaoEncontrado : AuthException()
    object ReautenticacaoNecessaria : AuthException()

    data class Desconhecido(val erroOriginal: Throwable?) : AuthException()
}