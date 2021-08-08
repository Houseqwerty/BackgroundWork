package com.example.backgroundwork.data.store

import android.content.SharedPreferences
import com.example.backgroundwork.models.data.UserStorable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val USERS_KEY = "USERS_KEY"

class UserStoreImpl(
    private val preferences: SharedPreferences,
    private val json: Json
) : UserStore {

    override fun saveUsers(users: List<UserStorable>) {
        if (users.isEmpty())
            return
        preferences.edit()
            .putString(USERS_KEY, json.encodeToString(users))
            .commit()
    }

    override fun getUsers(): List<UserStorable>? =
        preferences.getString(USERS_KEY, null)
            ?.let(json::decodeFromString)

}