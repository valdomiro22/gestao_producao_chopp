package com.santos.valdomiro.gestaoproducaochopp.di

import com.santos.valdomiro.gestaoproducaochopp.data.datasource.repository.AuthRepositoryImpl
import com.santos.valdomiro.gestaoproducaochopp.data.datasource.repository.StorageRepositoryImpl
import com.santos.valdomiro.gestaoproducaochopp.data.datasource.repository.UsuarioFirestoreFirestoreRepositoryImpl
import com.santos.valdomiro.gestaoproducaochopp.domain.repository.AuthRepository
import com.santos.valdomiro.gestaoproducaochopp.domain.repository.StorageRepository
import com.santos.valdomiro.gestaoproducaochopp.domain.repository.UsuarioFirestoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUsuarioRepository(
        usuarioFirestoreRepositoryImpl: UsuarioFirestoreFirestoreRepositoryImpl
    ): UsuarioFirestoreRepository

    @Binds
    @Singleton
    abstract fun bindStorageRepository(
        storageRepositoryImpl: StorageRepositoryImpl
    ): StorageRepository
}