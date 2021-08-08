package com.example.backgroundwork.models.converter

import com.example.backgroundwork.models.data.UserResponse
import com.example.backgroundwork.models.data.UserStore
import com.example.backgroundwork.models.domain.UserDomain

/**
 * Конвретер из [UserStore] в [UserDomain]
 *
 * @author Omelyuk Anton
 */
class UserStoreToUserDomainConverter : Converter<UserStore, UserDomain> {

    override fun convert(from: UserStore) =
        UserDomain(
            id = from.id,
            login = from.login,
            avatarUrl = from.avatarUrl,
        )
}