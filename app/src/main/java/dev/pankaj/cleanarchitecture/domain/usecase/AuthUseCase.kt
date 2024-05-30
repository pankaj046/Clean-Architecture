package dev.pankaj.cleanarchitecture.domain.usecase

import dev.pankaj.cleanarchitecture.data.remote.model.LoginRequest
import dev.pankaj.cleanarchitecture.domain.repository.IAuthRepository
import dev.pankaj.cleanarchitecture.utils.*

class AuthUseCase(private val iUserRepository: IAuthRepository){

    suspend fun login(loginRequest: LoginRequest): Result<String> {
        return iUserRepository.login(loginRequest)
    }
}