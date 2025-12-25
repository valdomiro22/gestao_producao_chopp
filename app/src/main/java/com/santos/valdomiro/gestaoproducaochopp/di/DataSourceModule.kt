package com.santos.valdomiro.gestaoproducaochopp.di

import com.santos.valdomiro.gestaoproducaochopp.data.datasource.AuthDataSource
import com.santos.valdomiro.gestaoproducaochopp.data.datasource.StorageDataSource
import com.santos.valdomiro.gestaoproducaochopp.data.datasource.UsuarioRemoteDataSource
import com.santos.valdomiro.gestaoproducaochopp.data.datasource.remote.AuthDataSourceImpl
import com.santos.valdomiro.gestaoproducaochopp.data.datasource.remote.StorageDataSourceImpl
import com.santos.valdomiro.gestaoproducaochopp.data.datasource.remote.UsuarioRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(
        authDataSource: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindUsuarioRemoteDataSource(
        impl: UsuarioRemoteDataSourceImpl
    ): UsuarioRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindStorageDataSource(
        impl: StorageDataSourceImpl
    ): StorageDataSource
}