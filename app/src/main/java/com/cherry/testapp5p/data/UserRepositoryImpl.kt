package com.cherry.testapp5p.data

import com.cherry.testapp5p.data.local.UserDatabase
import com.cherry.testapp5p.data.remote.LoginApi
import com.cherry.testapp5p.domain.UserRepository
import com.cherry.testapp5p.domain.model.User
import com.cherry.testapp5p.domain.model.UserRequestModel

class UserRepositoryImpl(
    private val api: LoginApi,
    db: UserDatabase
) : UserRepository {
    private val userDao = db.userDao

    override suspend fun getUser(phoneNumber: String): User {
        return try {
            userDao.getUser(phoneNumber.substring(4))
        } catch (e: StringIndexOutOfBoundsException) {
            userDao.getUser(phoneNumber)
        }
    }

    override suspend fun loginAndInsertUser(fullPhoneNumber: String, password: String) {
        val userRequestModel = UserRequestModel.createRequestModel(fullPhoneNumber, password)
        api.loginUserWithPhoneNumber(userRequestModel).body()?.let { userWrapper ->
            userDao.insertUser(user = userWrapper.user)
        }
    }
}