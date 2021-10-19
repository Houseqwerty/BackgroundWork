package com.example.backgroundwork.data.store

import android.content.SharedPreferences
import com.example.backgroundwork.models.data.UserStore
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val USERS_KEY = "USERS_KEY"

/**
 * Реализация [UsersStore] на [SharedPreferences]
 *
 * @param sharedPreferences кэш
 * @param jsonSerializer сериализатор
 */
class PreferencesUsersStoreImpl(
    private val sharedPreferences: SharedPreferences,
    private val jsonSerializer: Json
) : UsersStore {

    override fun saveUsers(users: List<UserStore>) {
        sharedPreferences.edit()
            .putString(USERS_KEY, jsonSerializer.encodeToString(users))
            .apply()
    }

    override fun getUsers(): List<UserStore>? {
        val usersString = sharedPreferences.getString(USERS_KEY, null)
        return usersString?.let(jsonSerializer::decodeFromString)
    }

}