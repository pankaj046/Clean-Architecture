package dev.pankaj.cleanarchitecture.data.dataSource.user

import dev.pankaj.cleanarchitecture.data.local.entity.UserEntity
import dev.pankaj.cleanarchitecture.data.local.dao.UserDao

class UserLocalDataSource(private val userDAO: UserDao) : IUserLocalDataSource {
    override suspend fun saveUser(user: UserEntity) {
        userDAO.insert(user)
    }
}