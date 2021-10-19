package com.example.backgroundwork.domain

import com.example.backgroundwork.models.data.UserStore

/**
 * Репозиторий для работы с данными
 */
interface UsersRepository {

    /**
     * Получить пользователей из БД, если пусто, то обновляем с сервера
     *
     * @return Список пользователй
     */
    fun getUsers(): List<UserStore>

    /**
     * Получить пользователей с сервера
     *
     * @return Список пользователй
     */
    fun getRemoteUsers(): List<UserStore>

}
