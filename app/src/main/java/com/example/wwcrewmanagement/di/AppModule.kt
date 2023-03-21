package com.example.wwcrewmanagement.di

import com.example.wwcrewmanagement.data.repository.WorkerRepositoryImpl
import com.example.wwcrewmanagement.domain.repository.WorkerRepository
import com.example.wwcrewmanagement.data.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi(): ApiClient = ApiClient()

    @Singleton
    @Provides
    fun provideWorkerRepository(api: ApiClient): WorkerRepository {
        return WorkerRepositoryImpl(api = api)
    }
}