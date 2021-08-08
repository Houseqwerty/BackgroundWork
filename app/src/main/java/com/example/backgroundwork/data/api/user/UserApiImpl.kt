package com.example.backgroundwork.data.api.user

import com.example.backgroundwork.data.api.GitHubUrl
import com.example.backgroundwork.models.data.UserResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Реализация [UserApi]
 *
 * @param okHttpClient клиент для работы с сетью
 * @param json сериализатор из Json в модельки
 */
class UserApiImpl(
    private val okHttpClient: OkHttpClient,
    private val json: Json
) : UserApi {

    override fun getUsers(): List<UserResponse> {
        val request = createGetRequest(GitHubUrl.USERS)
        Thread.sleep(3000)
        return json.decodeFromString(
            okHttpClient.newCall(request).execute().body?.string().orEmpty()
        )
    }

    override fun getUser(userId: String): UserResponse {
        val request = createGetRequest(GitHubUrl.USERS + userId)
        return okHttpClient.newCall(request).execute().use { response ->
            json.decodeFromString(response.body?.string().orEmpty())
        }
    }

    private fun createGetRequest(url: String): Request =
        Request.Builder()
            .url(url)
            .get()
            .build()

}