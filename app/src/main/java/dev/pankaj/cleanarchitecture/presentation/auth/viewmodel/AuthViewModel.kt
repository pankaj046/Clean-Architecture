package dev.pankaj.cleanarchitecture.presentation.auth.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import dev.pankaj.cleanarchitecture.App
import dev.pankaj.cleanarchitecture.data.remote.model.LoginRequest
import dev.pankaj.cleanarchitecture.domain.usecase.AuthUseCase
import dev.pankaj.cleanarchitecture.domain.usecase.UserUseCase
import kotlinx.coroutines.launch
import dev.pankaj.cleanarchitecture.utils.*
import dev.pankaj.cleanarchitecture.utils.NetworkUtils.isNetworkAvailable

@ViewModelScoped
class AuthViewModel(
    private val app: App,
    private val userUseCase: UserUseCase,
    private val authUseCase: AuthUseCase,
) : AndroidViewModel(app) {

    private val _loginResponse: MutableLiveData<Result<String>> = MutableLiveData()
    val loginResponse: LiveData<Result<String>> = _loginResponse

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _loginResponse.value = Result.Loading(true)
            if (isNetworkAvailable(app)){
                _loginResponse.value = Result.Message("No Internet connection")
            }
            authUseCase.login(loginRequest).apply {
                _loginResponse.value = this
                _loginResponse.value = Result.Loading(false)
            }
        }
    }

}