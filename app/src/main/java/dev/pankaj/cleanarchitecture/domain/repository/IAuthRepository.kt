package dev.pankaj.cleanarchitecture.domain.repository

import dev.pankaj.cleanarchitecture.data.remote.model.auth.LoginRequest
import dev.pankaj.cleanarchitecture.data.remote.model.auth.LoginResponse
import dev.pankaj.cleanarchitecture.utils.*

interface IAuthRepository {
    suspend fun login(loginRequest: LoginRequest): CallBack<LoginResponse>
}