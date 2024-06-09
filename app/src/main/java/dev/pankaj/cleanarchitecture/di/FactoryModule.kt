package dev.pankaj.cleanarchitecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pankaj.cleanarchitecture.App
import dev.pankaj.cleanarchitecture.domain.usecase.AuthUseCase
import dev.pankaj.cleanarchitecture.domain.usecase.ProductUseCase
import dev.pankaj.cleanarchitecture.domain.usecase.UserUseCase
import dev.pankaj.cleanarchitecture.presentation.auth.viewmodel.AuthViewModelFactory
import dev.pankaj.cleanarchitecture.presentation.home.viewmodel.ProductViewModelFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {


    @Singleton
    @Provides
    fun provideSignInViewModelFactory(
        app: App,
        userUseCase: UserUseCase,
        authUseCase: AuthUseCase,
    ): AuthViewModelFactory {
        return AuthViewModelFactory(
            app,
            userUseCase,
            authUseCase
        )
    }

    @Singleton
    @Provides
    fun provideProductViewModelFactory(
        app: App,
        productUseCase: ProductUseCase,
    ): ProductViewModelFactory {
        return ProductViewModelFactory(
            app,
            productUseCase
        )
    }
}