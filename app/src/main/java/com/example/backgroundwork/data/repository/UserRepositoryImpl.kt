package com.example.backgroundwork.data.repository

import com.example.backgroundwork.data.api.user.UserApi
import com.example.backgroundwork.data.store.UserStore
import com.example.backgroundwork.models.data.UserStorable

/**
 * Релазизация [UserRepository]
 */
class UserRepositoryImpl(
    private val userApi: UserApi,
    private val userStore: UserStore,
) : UserRepository {

    override fun getUsers(): List<UserStorable> =
        userStore.getUsers()
            ?: userApi.getUsers()
                .map { remoteUser -> UserStorable(remoteUser.login, remoteUser.avatarUrl) }
                .also(userStore::saveUsers)

    override fun getUser(userId: String): UserStorable =
        userApi.getUser(userId)
            .let { userResponse -> UserStorable(userResponse.login, userResponse.avatarUrl) }
}