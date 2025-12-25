package com.santos.valdomiro.gestaoproducaochopp.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.santos.valdomiro.gestaoproducaochopp.data.datasource.UsuarioRemoteDataSource
import com.santos.valdomiro.gestaoproducaochopp.data.dto.UsuarioDocument
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsuarioRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : UsuarioRemoteDataSource {
    val usuarioCollection = "usuarios"

    override suspend fun insertUser(usuario: UsuarioDocument) {
        if (usuario.id.isBlank()) {
            throw IllegalArgumentException("Erro: Tentativa de salvar usuario sem UID do Firebase Auth")
        }

        firestore
            .collection(usuarioCollection)
            .document(usuario.id)
            .set(usuario)
            .await()

    }

    override suspend fun updateUser(id: String, usuario: UsuarioDocument) {
        if (id.isBlank()) {
            throw IllegalArgumentException("ID do usuário não pode ser vazio")
        }

        firestore
            .collection(usuarioCollection)
            .document(id)
            .set(usuario, SetOptions.merge())  // merge() → atualiza só os campos existentes, não apaga os outros
            .await()
    }

    override suspend fun getUser(id: String): UsuarioDocument? {
        if (id.isBlank()) return null

        val snapshot = firestore
            .collection(usuarioCollection)
            .document(id)
            .get()
            .await()

        return snapshot.toObject(UsuarioDocument::class.java)
    }

    override suspend fun deleteUser(id: String) {
        if (id.isBlank()) {
            throw IllegalArgumentException("ID do usuário não pode ser vazio")
        }

        firestore
            .collection(usuarioCollection)
            .document(id)
            .delete()
            .await()
    }

    override suspend fun getAllUsers(): List<UsuarioDocument> {
        val snapshot = firestore
            .collection(usuarioCollection)
            .get()
            .await()

        return snapshot.documents.mapNotNull { doc ->
            doc.toObject(UsuarioDocument::class.java)
        }
    }
}