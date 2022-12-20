package com.kudos.focusincoroutines.section4.di

import com.kudos.focusincoroutines.section4.network.NewsApiService
import com.kudos.focusincoroutines.section4.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNewsRepository(apiService: NewsApiService): NewsRepository {
        return NewsRepository(apiService)
    }

}