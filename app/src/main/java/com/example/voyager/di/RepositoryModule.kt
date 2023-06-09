package com.example.voyager.di

import com.example.voyager.data.local.TourismDao
import com.example.voyager.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideRepository(tourismDao: TourismDao) = Repository(tourismDao)
}