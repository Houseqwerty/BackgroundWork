package com.example.backgroundwork.domain

import com.example.backgroundwork.data.repository.UserRepository
import com.example.backgroundwork.models.domain.UserDomain
import java.io.IOException


class UsersInteractor(
    private val userRepository: UserRepository,
) {

    @Throws(IOException::class, IllegalStateException::class)
    fun getUsers(): List<UserDomain> =
        userRepository.getUsers().filter { it.login.contains('d') }
            .map { UserDomain(it.login, it.avatarUrl) }

    @Throws(IOException::class, IllegalStateException::class)
    fun getUser(userId: String): UserDomain =
        userRepository.getUser(userId).let { UserDomain(it.login, it.avatarUrl) }

}