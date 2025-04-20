package com.example.tfg_appbanca.data.di


import com.example.tfg_appbanca.data.repositories.PantallaInicioRepository
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
    fun provideProductRepository(): PantallaInicioRepository {
        return PantallaInicioRepository()
    }
}