package com.santos.valdomiro.gestaoproducaochopp.data.datasource

import com.santos.valdomiro.gestaoproducaochopp.data.dto.UsuarioDocument

interface UsuarioRemoteDataSource {
    suspend fun insertUser(usuario: UsuarioDocument)
    suspend fun updateUser(id: String, usuario: UsuarioDocument)
    suspend fun getUser(id: String): UsuarioDocument?
    suspend fun deleteUser(id: String)
    suspend fun getAllUsers(): List<UsuarioDocument>
}