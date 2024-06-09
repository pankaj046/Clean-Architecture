package dev.pankaj.cleanarchitecture.data.dataSource.auth

import android.util.Log
import dev.pankaj.cleanarchitecture.data.remote.api.ApiService
import dev.pankaj.cleanarchitecture.data.remote.model.auth.LoginRequest
import dev.pankaj.cleanarchitecture.data.remote.model.auth.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class AuthDataSource @Inject constructor(private val api: ApiService) : IAuthDataSource {
    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return api.login(loginRequest)
    }
}