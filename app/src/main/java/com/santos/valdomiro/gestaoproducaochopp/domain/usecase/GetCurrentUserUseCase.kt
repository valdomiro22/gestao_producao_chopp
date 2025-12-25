package com.santos.valdomiro.gestaoproducaochopp.domain.usecase

import com.santos.valdomiro.gestaoproducaochopp.domain.model.Usuario
import com.santos.valdomiro.gestaoproducaochopp.domain.repository.AuthRepository
import com.santos.valdomiro.gestaoproducaochopp.domain.repository.UsuarioFirestoreRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val usuarioFirestoreRepository: UsuarioFirestoreRepository
) {

    suspend operator fun invoke(): Result<Usuario?> {
        return try {
            val uid = authRepository.getCurrentUserId()

            if (uid != null) {
                usuarioFirestoreRepository.getUser(uid)
            } else {
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(Exception("Erro ao buscar usuario"))
        }
    }

}