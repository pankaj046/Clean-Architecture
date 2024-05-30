package dev.pankaj.cleanarchitecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pankaj.cleanarchitecture.data.dataSource.IUserLocalDataSource
import dev.pankaj.cleanarchitecture.data.repository.UserRepository
import dev.pankaj.cleanarchitecture.domain.repository.IUserRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideUserRepository(
        userLocalDataSource: IUserLocalDataSource
    ): IUserRepository {
        return UserRepository(
            userLocalDataSource
        )
    }

}