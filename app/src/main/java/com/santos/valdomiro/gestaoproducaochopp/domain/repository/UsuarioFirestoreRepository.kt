package com.santos.valdomiro.gestaoproducaochopp.domain.repository

import com.santos.valdomiro.gestaoproducaochopp.domain.model.Usuario

interface UsuarioFirestoreRepository {
    suspend fun insertUser(usuario: Usuario): Result<Unit>
    suspend fun updateUser(id: String, usuario: Usuario): Result<Unit>
    suspend fun getUser(id: String): Result<Usuario?>
    suspend fun deleteUser(id: String): Result<Unit>
    suspend fun getAllUsers(): Result<List<Usuario>>
}