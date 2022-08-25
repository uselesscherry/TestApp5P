package com.cherry.testapp5p.data.remote

import com.cherry.testapp5p.domain.model.UserRequestModel
import com.cherry.testapp5p.domain.model.UserWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    companion object {
        const val BASE_URL = "https://tips-api-staging.crio-server.com"
    }

    @POST("/login")
    suspend fun loginUserWithPhoneNumber(
        @Body bodyParameters: UserRequestModel
    ): Response<UserWrapper>

}