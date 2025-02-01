package org.example.project.feature.auth.domain.repository

import org.example.project.feature.auth.domain.model.AuthResultData
import org.example.project.utils.SimpleResponse

interface AuthRepository {

    suspend fun signUp(
           name: String,
           email: String,
           password: String,
    ): SimpleResponse<AuthResultData>

    suspend fun signIn(
           email: String,
           password: String,
    ): SimpleResponse<AuthResultData>
}
