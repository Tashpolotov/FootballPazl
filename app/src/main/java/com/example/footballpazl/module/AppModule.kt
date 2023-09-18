package com.example.footballpazl.module

import com.example.data.PuzzleRepositoryMock
import com.example.domain.repository.PuzzleRepository
import com.example.domain.usecase.PuzzleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideRepository():PuzzleRepository{
        return PuzzleRepositoryMock()
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: PuzzleRepository):PuzzleUseCase{
        return PuzzleUseCase(repository)
    }
}