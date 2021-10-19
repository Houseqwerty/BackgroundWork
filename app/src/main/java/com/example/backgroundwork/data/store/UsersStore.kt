package com.example.backgroundwork.data.store

import com.example.backgroundwork.models.data.UserStore

/**
 * Стор для работы с БД
 *
 * @author Omelyuk Anton
 */
interface UsersStore {

    /**
     * Сохранить пользователей
     *
     * @param users пользователи, которых нужно сохранить
     */
    fun saveUsers(users: List<UserStore>)

    /**
     * Получить пользователей
     *
     * @return users пользователи из БД
     */
    fun getUsers(): List<UserStore>?

}
