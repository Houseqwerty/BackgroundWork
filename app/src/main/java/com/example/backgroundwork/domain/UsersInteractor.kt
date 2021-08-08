package com.example.backgroundwork.domain

import com.example.backgroundwork.models.converter.Converter
import com.example.backgroundwork.models.data.UserStore
import com.example.backgroundwork.models.domain.UserDomain

/**
 * Логика пользователей
 *
 * @param usersRepository репозиторий с пользователями
 * @param converter конвертер моделей из дата слоя в домейн
 */
class UsersInteractor(
    private val usersRepository: UsersRepository,
    private val converter: Converter<UserStore, UserDomain>
) {

    /**
     * Получить пользователей, у которых в логине содержится строка [loginPart]
     *
     * @param loginPart часть логина
     */
    fun getUsers(loginPart: String): List<UserDomain> {
        return usersRepository.getUsers()
            .filter { user -> user.login.contains(loginPart, true) }
            .map(converter::convert)
    }

    /**
     * Получить пользователей с сервера, у которых в логине содержится строка [loginPart]
     *
     * @param loginPart часть логина
     */
    fun remoteUsers(loginPart: String): List<UserDomain> {
        return usersRepository.getRemoteUsers()
            .filter { user -> user.login.contains(loginPart, true) }
            .map(converter::convert)
    }

}