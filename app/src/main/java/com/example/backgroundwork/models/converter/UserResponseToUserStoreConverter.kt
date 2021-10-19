package com.example.backgroundwork.models.converter

import com.example.backgroundwork.models.data.UserResponse
import com.example.backgroundwork.models.data.UserStore

/**
 * Конвретер из [UserResponse] в [UserStore]
 *
 * @author Omelyuk Anton
 */
class UserResponseToUserStoreConverter : Converter<UserResponse, UserStore> {

    override fun convert(from: UserResponse) =
        UserStore(
            id = from.id,
            login = from.login,
            avatarUrl = from.avatarUrl,
        )
}