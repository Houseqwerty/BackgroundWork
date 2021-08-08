package com.example.backgroundwork.data.repository

import com.example.backgroundwork.data.api.UsersApi
import com.example.backgroundwork.data.store.UsersStore
import com.example.backgroundwork.domain.UsersRepository
import com.example.backgroundwork.models.converter.Converter
import com.example.backgroundwork.models.data.UserResponse
import com.example.backgroundwork.models.data.UserStore

/**
 * Реализация [UsersRepository]
 *
 * @param usersApi апи для работы с сервераными данными
 * @param userStore стор для работы с данными из БД
 */
class UsersRepositoryImpl(
    private val usersApi: UsersApi,
    private val userStore: UsersStore,
    private val converter: Converter<UserResponse, UserStore>,
) : UsersRepository {

    override fun getUsers(): List<UserStore> = userStore.getUsers() ?: getRemoteUsers()

    override fun getRemoteUsers(): List<UserStore> =
        usersApi.getUsers()
            .map(converter::convert)
            .also(userStore::saveUsers)
}