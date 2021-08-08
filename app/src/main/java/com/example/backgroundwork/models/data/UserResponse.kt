package com.example.backgroundwork.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Модель данных пользователя
 *
 * @param login логин пользователя
 * @param avatarUrl url авантарки пользователя
 */
@Serializable
class UserResponse(
    @SerialName("login")
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String
)