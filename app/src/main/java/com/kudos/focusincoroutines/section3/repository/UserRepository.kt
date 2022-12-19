package com.kudos.focusincoroutines.section3.repository

import com.kudos.focusincoroutines.section3.db.UserDao
import com.kudos.focusincoroutines.section3.db.entity.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
) {

    suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }

    suspend fun getUser(userEmail: String): User {
        return userDao.getUser(userEmail)
    }

    suspend fun deleteUser(user: User) {
        return userDao.deleteUser(user)
    }
}