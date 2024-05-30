package dev.pankaj.cleanarchitecture.data.dataSource.auth

import dev.pankaj.cleanarchitecture.data.remote.api.ApiService
import dev.pankaj.cleanarchitecture.data.remote.model.LoginRequest
import retrofit2.Response

class AuthDataSource(private val api: ApiService) : IAuthDataSource {
    override suspend fun login(loginRequest: LoginRequest): Response<String> {
        return api.login(loginRequest)
    }
}