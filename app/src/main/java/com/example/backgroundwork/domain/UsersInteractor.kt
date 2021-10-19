package com.example.backgroundwork.domain

import com.example.backgroundwork.models.domain.UserDomain

/**
 * Логика пользователей
 *
 * @param usersRepository репозиторий с пользователями
 */
class UsersInteractor(
    private val usersRepository: UsersRepository,
) {

    /**
     * Получить пользователей, у которых в логине содержится строка [loginPart]
     *
     * @param loginPart часть логина
     */
    fun getUsers(loginPart: String): List<UserDomain> {
        return usersRepository.getUsers()
            .filter { user -> user.login.contains(loginPart, true) }
    }

    /**
     * Получить пользователей с сервера, у которых в логине содержится строка [loginPart]
     *
     * @param loginPart часть логина
     */
    fun remoteUsers(loginPart: String): List<UserDomain> {
        return usersRepository.getRemoteUsers()
            .filter { user -> user.login.contains(loginPart, true) }
    }

}