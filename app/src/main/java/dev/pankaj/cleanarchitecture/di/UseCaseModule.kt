package dev.pankaj.cleanarchitecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pankaj.cleanarchitecture.domain.repository.IUserRepository
import dev.pankaj.cleanarchitecture.domain.usecase.UserUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideUserUseCase(
        userRepository: IUserRepository
    ): UserUseCase {
        return UserUseCase(userRepository)
    }
}