package com.cherry.testapp5p.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @SerializedName("name")
    val name: String,
    @SerializedName("second_name")
    val surname: String,
    @SerializedName("phone_number")
    @PrimaryKey val phoneNumber: String
) {
    val fullPhoneNumber: String
        get() = "+380$phoneNumber"
}
