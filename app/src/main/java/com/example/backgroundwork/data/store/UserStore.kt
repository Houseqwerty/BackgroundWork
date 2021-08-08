package com.example.backgroundwork.data.store

import com.example.backgroundwork.models.data.UserStorable

/**
 * Хранилище пользователей
 *
 * @author Omelyuk Anton
 */
interface UserStore {

    /**
     * Сохрнить пользователей
     * @param users список пользователей
     */
    fun saveUsers(users: List<UserStorable>)

    /**
     * Получить всех пользователей
     *
     * @return список пользователей [UserStorable], [null] если пользователей нет
     */
    fun getUsers(): List<UserStorable>?

}