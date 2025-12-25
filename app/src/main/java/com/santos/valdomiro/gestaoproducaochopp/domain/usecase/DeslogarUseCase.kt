package com.santos.valdomiro.gestaoproducaochopp.domain.usecase

import com.santos.valdomiro.gestaoproducaochopp.domain.repository.AuthRepository
import javax.inject.Inject

class DeslogarUseCase @Inject constructor(
    private val auth: AuthRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        try {
            return auth.signOut()
        } catch (e: Exception) {
            throw Exception("Erro ao deslogar usuario: $e")
        }
    }
}