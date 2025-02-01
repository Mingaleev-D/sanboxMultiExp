package org.example.project.feature.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginParams(
       val email: String,
       val password: String
)

@Serializable
data class ResponseRequest(
       val message: String,
       @SerialName("access_token")
       val accessToken: String,
       @SerialName("token_type")
       val tokenType: String
)

@Serializable
data class ErrorLoginResponse(
       val message: String
)

@Serializable
data class RegisterParams(
       val name: String,
       val email: String,
       val password: String
)


@Serializable
data class ErrorRegisterResponse(
       val name: List<String>?,
       val email: List<String>?,
       val password: List<String>?
)
