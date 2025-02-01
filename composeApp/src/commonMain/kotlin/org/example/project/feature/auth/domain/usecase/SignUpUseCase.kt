package org.example.project.feature.auth.domain.usecase

import org.example.project.feature.auth.domain.model.AuthResultData
import org.example.project.feature.auth.domain.repository.AuthRepository
import org.example.project.utils.SimpleResponse

class SignUpUseCase(
       private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
           name: String,
           email: String,
           password: String
    ): SimpleResponse<AuthResultData> {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            return SimpleResponse.Error("Заполните все поля")
        }
        if (email.isBlank() || "@" !in email) {
            return SimpleResponse.Error("Некорректный email")
        }
        if (name.isBlank() || password.length < 3) {
            return SimpleResponse.Error("Некорректные данные")
        }
        if (password.isBlank() || password.length < 3) {
            return SimpleResponse.Error("Некорректные данные")
        }
        return authRepository.signUp(name, email, password)
    }
}
