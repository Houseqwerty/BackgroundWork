package com.example.backgroundwork.models.domain

/**
 * Модель данных для domain слоя
 *
 * @param id ид пользователя
 * @param login логин пользователля
 * @param avatarUrl аватар пользователя
 */
data class UserDomain(
    val id: Int,
    val login: String,
    val avatarUrl: String
)