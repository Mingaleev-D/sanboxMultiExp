package org.example.project.feature.auth.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import org.example.project.feature.auth.data.model.LoginParams
import org.example.project.feature.auth.data.model.RegisterParams
import org.example.project.feature.auth.data.model.ResponseRequest

class AuthService(private val client: HttpClient) {
    suspend fun signUp(registerParams: RegisterParams): ResponseRequest {
        return client.post {
            url("register") // Относительный путь (полный URL будет BASE_URL + "register")
            setBody(registerParams)
        }.body()
    }

    suspend fun signIn(loginParams: LoginParams): ResponseRequest {
        return client.post {
            url("login")
            setBody(loginParams)
        }.body()
    }
}
