package dev.pankaj.cleanarchitecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pankaj.cleanarchitecture.domain.repository.IAuthRepository
import dev.pankaj.cleanarchitecture.domain.repository.IProductRepository
import dev.pankaj.cleanarchitecture.domain.repository.IUserRepository
import dev.pankaj.cleanarchitecture.domain.usecase.AuthUseCase
import dev.pankaj.cleanarchitecture.domain.usecase.ProductUseCase
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


    @Singleton
    @Provides
    fun provideAuthUseCase(
        authRepository: IAuthRepository
    ): AuthUseCase {
        return AuthUseCase(authRepository)
    }


    @Singleton
    @Provides
    fun provideProductUseCase(
        productRepository: IProductRepository
    ): ProductUseCase {
        return ProductUseCase(productRepository)
    }
}

