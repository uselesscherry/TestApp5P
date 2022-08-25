package com.cherry.testapp5p.domain.model

import com.google.gson.annotations.SerializedName

data class UserWrapper(
    @SerializedName("user") var user: User
)
