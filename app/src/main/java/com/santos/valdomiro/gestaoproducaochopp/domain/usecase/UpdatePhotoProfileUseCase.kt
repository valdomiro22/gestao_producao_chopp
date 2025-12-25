package com.santos.valdomiro.gestaoproducaochopp.domain.usecase

import android.net.Uri
import com.santos.valdomiro.gestaoproducaochopp.domain.repository.StorageRepository
import com.santos.valdomiro.gestaoproducaochopp.domain.repository.UsuarioFirestoreRepository
import javax.inject.Inject

class UpdatePhotoProfileUseCase @Inject constructor(
    private val usuarioFirestoreRepository: UsuarioFirestoreRepository,
    private val storageRepository: StorageRepository,
) {

    val diretorioUsuarios = "usuarios"

    suspend operator fun invoke(uid: String, fileUri: Uri): Result<Unit> {
        return try {
            val usuarioResult = usuarioFirestoreRepository.getUser(uid)
            val usuario = usuarioResult.getOrNull() ?: return Result.failure(Exception("Usuario não encontrado"))

            if (usuario.fotoUrl.isNotBlank()) {
                storageRepository.deleteFile(usuario.fotoUrl)
            }

            val destino = "$diretorioUsuarios/$uid"
            val urlImage = storageRepository.uploadFile(destino, fileUri).getOrThrow()

            val usuarioAtualizado = usuario.copy(fotoUrl = urlImage)

            return usuarioFirestoreRepository.updateUser(uid, usuarioAtualizado)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}