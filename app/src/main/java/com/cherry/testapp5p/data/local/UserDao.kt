package com.cherry.testapp5p.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cherry.testapp5p.domain.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE phoneNumber =:phoneNumber")
    suspend fun getUser(phoneNumber: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
}