package com.example.backgroundwork.data.api.user

import com.example.backgroundwork.models.data.UserResponse
import java.io.IOException
import java.lang.IllegalStateException

/**
 * Api для работы с пользователями
 */
interface UserApi {

    /**
     * Список пользователей
     * @return список пользователей [UserResponse]
     */
    @Throws(IOException::class, IllegalStateException::class)
    fun getUsers(): List<UserResponse>

    /**
     * Получить пользователя по [userId]
     *
     * @param userId id пользователя
     *
     * @return пользователь [UserResponse]
     * */
    @Throws(IOException::class, IllegalStateException::class)
    fun getUser(userId: String): UserResponse

}