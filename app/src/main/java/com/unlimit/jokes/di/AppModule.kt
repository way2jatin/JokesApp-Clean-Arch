package com.unlimit.jokes.di

import com.unlimit.data.repository.JokesRepositoryImp
import com.unlimit.data.source.local.JokesLocalDataSource
import com.unlimit.data.source.local.JokesLocalDataSourceImp
import com.unlimit.data.source.local.base.AppDatabase
import com.unlimit.data.source.remote.JokesCloudDataSource
import com.unlimit.data.source.remote.JokesCloudDataSourceImp
import com.unlimit.data.source.remote.base.JokesApi
import com.unlimit.domain.repository.JokesRepository
import com.unlimit.domain.usecase.GetJokesUseCase
import com.unlimit.presentation.jokes.JokesViewModel
import org.koin.dsl.module

val appModule = module {

    //Repository & Data sources
    single { createJokesRepository(get(), get()) }

    single { createJokesRemoteDataSource(get()) }

    single { createJokesLocalDataSource(get()) }

    single { createGetJokesUseCase(get()) }

    single { JokesViewModel(get()) }
}

fun createJokesRepository(
    jokesCloudDataSource: JokesCloudDataSource,
    jokesLocalDataSource: JokesLocalDataSource
): JokesRepository {
    return JokesRepositoryImp(jokesCloudDataSource, jokesLocalDataSource)
}

fun createJokesRemoteDataSource(jokesService: JokesApi): JokesCloudDataSource {
    return JokesCloudDataSourceImp(jokesService)
}

fun createJokesLocalDataSource(appDatabase: AppDatabase): JokesLocalDataSource {
    return JokesLocalDataSourceImp(appDatabase)
}

fun createGetJokesUseCase(jokesRepository: JokesRepository): GetJokesUseCase {
    return GetJokesUseCase(jokesRepository)
}