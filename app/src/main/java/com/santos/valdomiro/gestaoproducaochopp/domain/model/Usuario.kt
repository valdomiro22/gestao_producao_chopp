package com.santos.valdomiro.gestaoproducaochopp.domain.model

import java.time.LocalDate

data class Usuario(
    val id: String = "",
    val nome: String = "",
    val sobrenome: String = "",
    val idade: Int = 0,
    val email: String = "",
    val fotoUrl: String = "",
    val adicionadoEm: LocalDate? = null,
)