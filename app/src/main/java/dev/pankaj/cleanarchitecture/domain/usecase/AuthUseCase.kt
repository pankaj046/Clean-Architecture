package dev.pankaj.cleanarchitecture.domain.usecase

import dev.pankaj.cleanarchitecture.data.remote.model.auth.LoginRequest
import dev.pankaj.cleanarchitecture.data.remote.model.auth.LoginResponse
import dev.pankaj.cleanarchitecture.domain.repository.IAuthRepository
import dev.pankaj.cleanarchitecture.utils.*

class AuthUseCase(private val iUserRepository: IAuthRepository){

    suspend fun login(loginRequest: LoginRequest): CallBack<LoginResponse> {
        return iUserRepository.login(loginRequest)
    }
}
