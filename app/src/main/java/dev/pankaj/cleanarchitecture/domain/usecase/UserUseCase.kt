package dev.pankaj.cleanarchitecture.domain.usecase

import dev.pankaj.cleanarchitecture.data.local.entity.UserEntity
import dev.pankaj.cleanarchitecture.domain.repository.IUserRepository

class UserUseCase(private val iUserRepository: IUserRepository){

    suspend fun saveUser(user: UserEntity){
        iUserRepository.saveUser(user)
    }
}
