package dev.pankaj.cleanarchitecture.presentation.auth.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import dev.pankaj.cleanarchitecture.App
import dev.pankaj.cleanarchitecture.data.remote.model.LoginRequest
import dev.pankaj.cleanarchitecture.data.remote.model.LoginResponse
import dev.pankaj.cleanarchitecture.domain.model.auth.LoginUi
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

    private val _loginResponse: MutableLiveData<CallBack<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<CallBack<LoginResponse>> = _loginResponse

    private val _loginValidate = MutableLiveData<CallBack<Boolean>>()
    val loginValidate: LiveData<CallBack<Boolean>> = _loginValidate

    fun login(loginRequest: LoginRequest) = viewModelScope.launch {
        _loginResponse.value = CallBack.Loading(true)
        if (isNetworkAvailable(app)){
            authUseCase.login(loginRequest).apply {
                _loginResponse.value = this
                _loginResponse.value = CallBack.Loading(false)
            }
        }else {
            _loginResponse.value = CallBack.Message("No Internet connection")
            _loginResponse.value = CallBack.Loading(false)
        }
    }

    fun validateLogin(email: String, password: String) = viewModelScope.launch {
        if (email.isEmpty()){
            _loginValidate.value = CallBack.Message("Invalid username")
        }
        if (password.isEmpty()){
            _loginValidate.value = CallBack.Message("Invalid password")
        }
        if (email.isNotEmpty() && password.isNotEmpty()){
            _loginValidate.value = CallBack.Success(true)
        }
    }
}