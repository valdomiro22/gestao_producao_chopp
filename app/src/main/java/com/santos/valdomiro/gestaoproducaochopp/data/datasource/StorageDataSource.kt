package com.santos.valdomiro.gestaoproducaochopp.data.datasource

interface StorageDataSource {
    // Faz o upload e retorna o link (Download URL) em caso de sucesso
    suspend fun uploadFile(path: String, fileUri: android.net.Uri): Result<String>

    // Apenas recupera o link de um arquivo que já existe
    suspend fun getDownloadUrl(fileUrl: String): Result<String>

    // Deleta o arquivo baseado no caminho/referência
    suspend fun deleteFile(fileUrl: String): Result<Unit>
}