package com.santos.valdomiro.gestaoproducaochopp.domain.usecase

import com.santos.valdomiro.gestaoproducaochopp.domain.repository.AuthRepository
import javax.inject.Inject

class LogarUsuarioUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, senha: String): Result<String> {
        try {
            return authRepository.login(email, senha)
        } catch (e: Exception) {
            throw e
        }
    }
}