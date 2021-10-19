package com.example.backgroundwork.domain

import com.example.backgroundwork.models.domain.UserDomain

/**
 * Репозиторий для работы с данными
 */
interface UsersRepository {

    /**
     * Получить пользователей из БД, если пусто, то обновляем с сервера
     *
     * @return Список пользователй
     */
    fun getUsers(): List<UserDomain>

    /**
     * Получить пользователей с сервера
     *
     * @return Список пользователй
     */
    fun getRemoteUsers(): List<UserDomain>

}
