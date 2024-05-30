package dev.pankaj.cleanarchitecture.data.dataSource.auth

import dev.pankaj.cleanarchitecture.data.remote.api.ApiService
import dev.pankaj.cleanarchitecture.data.remote.model.LoginRequest
import retrofit2.Response
import javax.inject.Inject

class AuthDataSource @Inject constructor(private val api: ApiService) : IAuthDataSource {
    override suspend fun login(loginRequest: LoginRequest): Response<String> {
        return api.login(loginRequest)
    }
}