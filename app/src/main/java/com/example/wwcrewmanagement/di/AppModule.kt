package com.example.wwcrewmanagement.di

import android.content.Context
import androidx.room.Room
import com.example.wwcrewmanagement.data.local.database.WorkerDao
import com.example.wwcrewmanagement.data.local.database.WorkerDatabase
import com.example.wwcrewmanagement.data.network.ApiClient
import com.example.wwcrewmanagement.data.repository.WorkerRepositoryImpl
import com.example.wwcrewmanagement.domain.repository.WorkerRepository
import com.example.wwcrewmanagement.domain.use_case.GetWorkerByIdUseCase
import com.example.wwcrewmanagement.domain.use_case.GetWorkersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideWorkerRepository(workerDao: WorkerDao, api: ApiClient): WorkerRepository {
        return WorkerRepositoryImpl(workerDao, api = api)
    }

    @Provides
    @Singleton
    fun provideWorkerDatabase(@ApplicationContext context: Context): WorkerDatabase {
        return Room.databaseBuilder(
            context,
            WorkerDatabase::class.java,
            "worker_db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideWorkerDao(database: WorkerDatabase): WorkerDao {
        return database.workerDao()
    }


    @Provides
    @Singleton
    fun provideGetWorkersUseCase(workerRepository: WorkerRepository): GetWorkersUseCase {
        return GetWorkersUseCase(workerRepository)
    }

    @Provides
    @Singleton
    fun provideGetWorkerByIdUseCase(workerRepository: WorkerRepository): GetWorkerByIdUseCase {
        return GetWorkerByIdUseCase(workerRepository)
    }
}