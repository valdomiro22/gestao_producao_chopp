package com.santos.valdomiro.gestaoproducaochopp.data.datasource.repository

import android.net.Uri
import com.santos.valdomiro.gestaoproducaochopp.data.datasource.StorageDataSource
import com.santos.valdomiro.gestaoproducaochopp.domain.repository.StorageRepository
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storageDataSource: StorageDataSource
) : StorageRepository {

    override suspend fun uploadFile(path: String, fileUri: Uri): Result<String> {
        return storageDataSource.uploadFile(path, fileUri)
    }

    override suspend fun getDownloadUrl(fileUrl: String): Result<String> {
        return storageDataSource.getDownloadUrl(fileUrl)
    }

    override suspend fun deleteFile(fileUrl: String): Result<Unit> {
        return storageDataSource.deleteFile(fileUrl)
    }
}