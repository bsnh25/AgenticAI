package com.learnagentic.core.data.di

import com.learnagentic.core.data.repository.ModuleRepositoryImpl
import com.learnagentic.core.domain.repository.ModuleRepository
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
    abstract fun bindModuleRepository(
        moduleRepositoryImpl: ModuleRepositoryImpl
    ): ModuleRepository
}
