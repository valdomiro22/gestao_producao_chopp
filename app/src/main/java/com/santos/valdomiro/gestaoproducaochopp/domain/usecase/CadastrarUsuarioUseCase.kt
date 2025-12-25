package com.santos.valdomiro.gestaoproducaochopp.domain.usecase

import com.santos.valdomiro.gestaoproducaochopp.data.datasource.repository.AuthRepositoryImpl
import com.santos.valdomiro.gestaoproducaochopp.data.datasource.repository.UsuarioFirestoreFirestoreRepositoryImpl
import com.santos.valdomiro.gestaoproducaochopp.domain.model.Usuario
import javax.inject.Inject

class CadastrarUsuarioUseCase @Inject constructor(
    private val authRepository: AuthRepositoryImpl,
    private val usuarioRepository: UsuarioFirestoreFirestoreRepositoryImpl
) {

    suspend operator fun invoke(usuario: Usuario, password: String, email: String) : Result<String> {
        return runCatching {
            val novoId = authRepository.createUser(email, password).getOrThrow()
            val usuarioConId = usuario.copy(id = novoId)
            usuarioRepository.insertUser(usuarioConId)
            novoId
        }
    }
}