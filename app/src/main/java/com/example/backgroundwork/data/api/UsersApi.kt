package com.example.backgroundwork.data.api

import com.example.backgroundwork.models.data.UserResponse

/**
 *
 * Апи для получения данных о пользователе
 *
 * @author Omelyuk Anton
 */
interface UsersApi {

    /**
     * Полчить список пользователей
     *
     * @return [UserResponse] список пользователей или пустой список, если ошибка
     */
    fun getUsers(): List<UserResponse>

}