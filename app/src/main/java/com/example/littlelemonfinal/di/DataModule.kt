package com.example.littlelemonfinal.di

import com.example.littlelemonfinal.model.data.repositories.HomeRepository
import com.example.littlelemonfinal.model.data.repositories.HomeRepositoryImpl
import com.example.littlelemonfinal.model.data.repositories.sources.HomeDataSource
import com.example.littlelemonfinal.model.data.repositories.sources.HomeLocalDataSource
import com.example.littlelemonfinal.model.data.repositories.sources.HomeRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@DisableInstallInCheck
object DataModule {


    @Module
    @DisableInstallInCheck
    interface Bindings{
        @Binds
        @Singleton
        fun provideHomeRepository(homeRepositoryImpl: HomeRepositoryImpl) : HomeRepository



        @Binds
        @Singleton
        @HomeLocalQualifier
        fun provideHomeLocalDataSource(homeLocalDataSource: HomeLocalDataSource) : HomeDataSource


        @Binds
        @Singleton
        @HomeRemoteQualifier
        fun provideHomeRemoteDataSource(homeRemoteDataSource: HomeRemoteDataSource) : HomeDataSource








    }

}