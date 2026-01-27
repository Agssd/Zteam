package com.example.zteam.database

import com.example.zteam.auth.UserDao
import com.example.zteam.auth.UserEntity

class UserRepository(private val userDao: UserDao) {
    suspend fun register(user: UserEntity) = userDao.registerUser(user)

    suspend fun login(username: String, passwordHash: String): UserEntity? =
        userDao.login(username, passwordHash)

    suspend fun getUser(username: String): UserEntity? =
        userDao.getUser(username)
}