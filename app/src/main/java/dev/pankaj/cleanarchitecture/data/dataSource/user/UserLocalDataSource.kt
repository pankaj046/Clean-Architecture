package dev.pankaj.cleanarchitecture.data.dataSource.user

import dev.pankaj.cleanarchitecture.data.local.entity.UserEntity
import dev.pankaj.cleanarchitecture.data.local.dao.UserDao
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val userDAO: UserDao) : IUserLocalDataSource {
    override suspend fun saveUser(user: UserEntity) {
        userDAO.insert(user)
    }
}