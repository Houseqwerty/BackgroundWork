package com.example.backgroundwork.data.repository

import com.example.backgroundwork.data.api.UsersApi
import com.example.backgroundwork.data.store.UsersStore
import com.example.backgroundwork.domain.UsersRepository
import com.example.backgroundwork.models.converter.Converter
import com.example.backgroundwork.models.data.UserResponse
import com.example.backgroundwork.models.data.UserStore
import com.example.backgroundwork.models.domain.UserDomain

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
    private val converterDomain: Converter<UserStore, UserDomain>,
) : UsersRepository {

    override fun getUsers(): List<UserDomain> =
        userStore.getUsers()?.map(converterDomain::convert) ?: getRemoteUsers()

    override fun getRemoteUsers(): List<UserDomain> =
        usersApi.getUsers()
            .map(converter::convert)
            .also(userStore::saveUsers)
            .map(converterDomain::convert)
}