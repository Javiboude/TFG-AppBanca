package com.example.tfg_appbanca.data.di


import com.example.tfg_appbanca.data.repositories.GetRepository
import com.example.tfg_appbanca.data.repositories.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductRepository(): GetRepository {
        return GetRepository()
    }

    @Provides
    @Singleton
    fun provideProductRepository2(): PostRepository {
        return PostRepository()
    }
}