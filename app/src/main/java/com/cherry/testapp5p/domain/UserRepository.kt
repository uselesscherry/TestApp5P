package com.cherry.testapp5p.domain

import com.cherry.testapp5p.domain.model.User

interface UserRepository {

    suspend fun getUser(phoneNumber: String): User

    suspend fun loginAndInsertUser(fullPhoneNumber: String, password: String)

}