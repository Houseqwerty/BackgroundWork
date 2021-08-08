package com.example.backgroundwork.models.converter

import com.example.backgroundwork.models.domain.UserDomain
import com.example.backgroundwork.models.presentation.UserModel

/**
 * Конвретер из [UserDomain] в [UserModel]
 *
 * @author Omelyuk Anton
 */
class UserDomainToUserModelConverter : Converter<UserDomain, UserModel> {

    override fun convert(from: UserDomain) =
        UserModel(
            id = from.id,
            login = from.login,
            avatarUrl = from.avatarUrl,
        )
}