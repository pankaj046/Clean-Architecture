package dev.pankaj.cleanarchitecture.data.repository

import dev.pankaj.cleanarchitecture.data.dataSource.IUserLocalDataSource
import dev.pankaj.cleanarchitecture.data.local.entity.UserEntity
import dev.pankaj.cleanarchitecture.domain.repository.IUserRepository

class UserRepository(private val userLocalDataSource: IUserLocalDataSource) : IUserRepository {
    override suspend fun saveUser(user: UserEntity) {
        userLocalDataSource.saveUser(user)
    }
}