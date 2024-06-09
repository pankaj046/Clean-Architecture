package dev.pankaj.cleanarchitecture.domain.repository

import dev.pankaj.cleanarchitecture.data.remote.model.LoginRequest
import dev.pankaj.cleanarchitecture.data.remote.model.LoginResponse
import dev.pankaj.cleanarchitecture.utils.*

interface IAuthRepository {
    suspend fun login(loginRequest: LoginRequest): CallBack<LoginResponse>
}