package dev.pankaj.cleanarchitecture.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pankaj.cleanarchitecture.data.dataSource.auth.AuthDataSource
import dev.pankaj.cleanarchitecture.data.dataSource.auth.IAuthDataSource
import dev.pankaj.cleanarchitecture.data.dataSource.user.IUserLocalDataSource
import dev.pankaj.cleanarchitecture.data.dataSource.user.UserLocalDataSource
import dev.pankaj.cleanarchitecture.data.local.dao.UserDao
import dev.pankaj.cleanarchitecture.data.remote.api.ApiService

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideUserLocalDataSource(userDAO: UserDao): IUserLocalDataSource {
        return UserLocalDataSource(userDAO)
    }

    @Provides
    fun provideAuthDataSource(apiService: ApiService): IAuthDataSource {
        return AuthDataSource(apiService)
    }
}