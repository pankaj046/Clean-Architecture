package dev.pankaj.cleanarchitecture.domain.repository

import dev.pankaj.cleanarchitecture.data.local.entity.UserEntity

interface IUserRepository {
    suspend fun saveUser(user: UserEntity)
}