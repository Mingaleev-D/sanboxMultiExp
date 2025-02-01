package org.example.project.feature.auth.data.repository

import kotlinx.coroutines.withContext
import org.example.project.feature.auth.data.AuthMappers.toAuthResultData
import org.example.project.feature.auth.data.model.LoginParams
import org.example.project.feature.auth.data.model.RegisterParams
import org.example.project.feature.auth.data.remote.AuthService
import org.example.project.feature.auth.domain.model.AuthResultData
import org.example.project.feature.auth.domain.repository.AuthRepository
import org.example.project.utils.DispatcherProvider
import org.example.project.utils.SimpleResponse

class AuthRepositoryImpl(
       private val authService: AuthService,
       private val dispatcherProvider: DispatcherProvider
) : AuthRepository {

    override suspend fun signUp(
           name: String,
           email: String,
           password: String
    ): SimpleResponse<AuthResultData> {
        return withContext(dispatcherProvider.io) {
            try {
                val request = RegisterParams(name = name, email = email, password = password)
                val authResponse = authService.signUp(request)

                if (authResponse.message.isNullOrBlank() || authResponse.accessToken.isNullOrBlank() || authResponse.tokenType.isNullOrBlank()) {
                    SimpleResponse.Error("Некорректный ответ от сервера") // Проверяем на пустые или null поля
                } else {
                    SimpleResponse.Success(authResponse.toAuthResultData())
                }
            } catch (ex: Exception) {
                SimpleResponse.Error("Ошибка регистрации: ${ex.message}") // Получаем сообщение, а не объект исключения
            }
        }
    }

    override suspend fun signIn(
           email: String,
           password: String
    ): SimpleResponse<AuthResultData> {
        return withContext(dispatcherProvider.io) {
            try {
                val request = LoginParams(
                       email = email,
                       password = password
                ) // Предполагаем, что у вас есть класс данных LoginParams
                val authResponse = authService.signIn(request)

                if (authResponse.message.isNullOrBlank() || authResponse.accessToken.isNullOrBlank() || authResponse.tokenType.isNullOrBlank()) {
                    SimpleResponse.Error("Некорректный ответ от сервера")
                } else {
                    SimpleResponse.Success(authResponse.toAuthResultData())
                }
            } catch (ex: Exception) {
                SimpleResponse.Error("Ошибка входа: ${ex.message}")
            }
        }
    }
}
