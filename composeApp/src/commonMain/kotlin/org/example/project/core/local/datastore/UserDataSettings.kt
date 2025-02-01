package org.example.project.core.local.datastore

import kotlinx.serialization.Serializable
import org.example.project.feature.auth.domain.model.AuthResultData

/**
тут сохраняются данные пользователя после успешной аунтификации
данных может быть больше
например: аватар, name
нашей api таких данных нет (нам в приципе нужен только токен)
 */

/*
{
    "message": "Hi Sangam Singh, Welcome to TechBlog By CoderSangam",
    "access_token": "2434|y1pDnjMXluIqPCGUYB8BRvwmWOa27yAUSjSHdY8Q",
    "token_type": "Bearer"
}
 */
@Serializable
data class UserDataSettings(
       val message: String = "",
       val accessToken: String = "",
       val tokenType: String
)

fun UserDataSettings.toAuthResultData(): AuthResultData {
    return AuthResultData(
           message = this.message,
           accessToken = this.accessToken,
           tokenType = this.tokenType
    )
}

fun AuthResultData.toUserData(): UserDataSettings {
    return UserDataSettings(
           message = this.message,
           accessToken = this.accessToken,
           tokenType = this.tokenType
    )
}
