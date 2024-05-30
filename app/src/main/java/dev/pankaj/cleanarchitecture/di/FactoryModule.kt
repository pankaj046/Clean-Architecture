package dev.pankaj.cleanarchitecture.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pankaj.cleanarchitecture.App
import dev.pankaj.cleanarchitecture.domain.usecase.AuthUseCase
import dev.pankaj.cleanarchitecture.domain.usecase.UserUseCase
import dev.pankaj.cleanarchitecture.presentation.auth.viewmodel.AuthViewModelFactory
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


}