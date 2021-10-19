package com.example.backgroundwork.data.api

import com.example.backgroundwork.data.GitHubUrl
import com.example.backgroundwork.models.data.UserResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Реализация [UsersApi] с помощью [OkHttpClient]
 *
 * @param okHttpClient клиент для запросов в сеть
 * @param jsonSerializer сериализатор
 */
class OkHttpUsersApiImpl(
    private val okHttpClient: OkHttpClient,
    private val jsonSerializer: Json
) : UsersApi {

    override fun getUsers(): List<UserResponse> {
        val request = Request.Builder()
            .url(GitHubUrl.USERS)
            .get()
            .build()
        val body = okHttpClient.newCall(request).execute().body

        return body?.string()?.let(jsonSerializer::decodeFromString) ?: emptyList()
    }

}

