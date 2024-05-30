package dev.pankaj.cleanarchitecture.data.repository.auth

import dev.pankaj.cleanarchitecture.data.dataSource.auth.IAuthDataSource
import dev.pankaj.cleanarchitecture.data.dataSource.user.IUserLocalDataSource
import dev.pankaj.cleanarchitecture.data.local.entity.UserEntity
import dev.pankaj.cleanarchitecture.data.remote.model.LoginRequest
import dev.pankaj.cleanarchitecture.domain.repository.IAuthRepository
import dev.pankaj.cleanarchitecture.domain.repository.IUserRepository
import dev.pankaj.cleanarchitecture.utils.*

class AuthRepository(private val authDataSource: IAuthDataSource) : IAuthRepository {

    override suspend fun login(loginRequest: LoginRequest): Result<String> {
        return try {
            val response = authDataSource.login(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it)
                } ?: Result.Message("Response body is null")
            } else {
                Result.Message("Login failed with code: ${response.code()}")
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}