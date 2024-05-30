package dev.pankaj.cleanarchitecture.presentation.auth.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.pankaj.cleanarchitecture.App
import dev.pankaj.cleanarchitecture.domain.usecase.AuthUseCase
import dev.pankaj.cleanarchitecture.domain.usecase.UserUseCase


class AuthViewModelFactory(
    private val app: App,
    private val userUseCase: UserUseCase,
    private val authUseCase: AuthUseCase,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(
            app,
            userUseCase,
            authUseCase
        ) as T
    }
}