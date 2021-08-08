package com.example.backgroundwork.models.domain

/**
 * Модель данных для логического слоя
 *
 * @param login логин
 * @param avatarUrl  аватар пользователя
 */
data class UserDomain(
    val login: String,
    val avatarUrl: String
)