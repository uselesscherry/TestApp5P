package com.cherry.testapp5p.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cherry.testapp5p.domain.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DB_NAME = "user.db"
    }
}