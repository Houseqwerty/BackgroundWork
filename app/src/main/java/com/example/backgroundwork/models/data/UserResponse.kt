package com.example.backgroundwork.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 *
 * Модель для работы с сервером
 *
 * @param id ид пользователя
 * @param login логин пользователя
 * @param avatarUrl аватар пользователя
 *
 * @author Anton Omelyuk
 */
@Serializable
data class UserResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("login")
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
)