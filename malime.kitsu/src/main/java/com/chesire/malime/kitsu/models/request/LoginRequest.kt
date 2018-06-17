package com.chesire.malime.kitsu.models.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val username: String,
    val password: String,
    @SerializedName("grant_type")
    val grantType: String = "password"
)