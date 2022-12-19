package com.kudos.focusincoroutines.section2.di

import com.kudos.focusincoroutines.section2.network.ApiService
import com.kudos.focusincoroutines.section2.repository.CountryRepository
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
    fun provideCountryRepository(apiService: ApiService): CountryRepository {
        return CountryRepository(apiService)
    }

}