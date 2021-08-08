package com.example.backgroundwork.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserStorable(
    @SerialName("login_storable")
    val login: String,
    @SerialName("avatar_url_storable")
    val avatarUrl: String
)