package com.santos.valdomiro.gestaoproducaochopp.data.dto

import java.util.Date

data class UsuarioDocument(
    val id: String = "",
    val nome: String = "",
    val sobrenome: String = "",
    val idade: Int = 0,
    val email: String = "",
    val fotoUrl: String = "",
    val adicionadoEm: Date? = null,
)