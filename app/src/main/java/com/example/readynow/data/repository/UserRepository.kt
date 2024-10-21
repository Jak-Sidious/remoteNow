package com.example.readynow.data.repository

import com.example.readynow.data.model.User
import com.example.readynow.data.model.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val dao: UserDao) {
    fun getAll() = dao.getAll()
    suspend fun insert(entity: User) = dao.insert(entity)
}