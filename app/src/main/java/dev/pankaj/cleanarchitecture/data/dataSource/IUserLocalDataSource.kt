package dev.pankaj.cleanarchitecture.data.dataSource

import dev.pankaj.cleanarchitecture.data.local.entity.UserEntity

interface IUserLocalDataSource {
    suspend fun saveUser(user: UserEntity)
}