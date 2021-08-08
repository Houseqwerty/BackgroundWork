package com.example.backgroundwork.data.repository

import com.example.backgroundwork.models.data.UserStorable
import java.io.IOException
import java.lang.IllegalStateException

/**
 * Репозиторий с пользователями
 *
 * @author Omelyuk Anton
 */
interface UserRepository {

    /**
     * Список пользователей
     * @return список пользователей [UserStorable]
     */
    @Throws(IOException::class, IllegalStateException::class)
    fun getUsers(): List<UserStorable>

    /**
     * Получить пользователя по [userId]
     *
     * @param userId id пользователя
     *
     * @return пользователь [UserStorable]
     * */
    @Throws(IOException::class, IllegalStateException::class)
    fun getUser(userId: String): UserStorable

}