package com.santos.valdomiro.gestaoproducaochopp.data.mapper

import com.santos.valdomiro.gestaoproducaochopp.data.dto.UsuarioDocument
import com.santos.valdomiro.gestaoproducaochopp.domain.model.Usuario
import java.time.ZoneId
import java.util.Date

fun UsuarioDocument.toModel(): Usuario {
    return Usuario(
        id = this.id,
        nome = this.nome,
        sobrenome = this.sobrenome,
        idade = this.idade,
        email = this.email,
        fotoUrl = this.fotoUrl,
        adicionadoEm = this.adicionadoEm?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDate()
    )
}

fun Usuario.toDocument(): UsuarioDocument {
    return UsuarioDocument(
        id = this.id,
        nome = this.nome,
        sobrenome = this.sobrenome,
        idade = this.idade,
        email = this.email,
        fotoUrl = this.fotoUrl,
        adicionadoEm = this.adicionadoEm?.let {
            Date.from(it.atStartOfDay(ZoneId.systemDefault()).toInstant())
        }
    )
}