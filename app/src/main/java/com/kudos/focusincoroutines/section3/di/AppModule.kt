package com.kudos.focusincoroutines.section3.di

import com.kudos.focusincoroutines.section2.network.ApiService
import com.kudos.focusincoroutines.section2.repository.CountryRepository
import com.kudos.focusincoroutines.section3.db.UserDao
import com.kudos.focusincoroutines.section3.repository.UserRepository
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
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

}