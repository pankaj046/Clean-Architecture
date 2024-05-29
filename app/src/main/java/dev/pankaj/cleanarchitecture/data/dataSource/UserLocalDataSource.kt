package dev.pankaj.cleanarchitecture.data.dataSource

import dev.pankaj.cleanarchitecture.data.local.entity.UserEntity

class UserLocalDataSource(private val userDAO: UserDAO) : IUserLocalDataSource {
    suspend fun saveUser(user: UserEntity) {
        userDAO.save(user)
    }
}