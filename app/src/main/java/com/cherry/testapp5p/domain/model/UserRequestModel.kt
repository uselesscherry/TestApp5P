package com.cherry.testapp5p.domain.model

import com.google.gson.annotations.SerializedName

class UserRequestModel(
    @SerializedName("phone_code")
    val phoneCode: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("password")
    val password: String
) {
    companion object {
        fun createRequestModel(fullPhoneNumber: String, password: String): UserRequestModel {
            val countryCode = try {
                fullPhoneNumber.substring(0, 4)
            } catch (e: StringIndexOutOfBoundsException) {
                fullPhoneNumber
            }
            val phoneNumber = try {
                fullPhoneNumber.substring(4)
            } catch (e: StringIndexOutOfBoundsException) {
                fullPhoneNumber
            }

            return UserRequestModel(countryCode, phoneNumber, password)
        }
    }
}