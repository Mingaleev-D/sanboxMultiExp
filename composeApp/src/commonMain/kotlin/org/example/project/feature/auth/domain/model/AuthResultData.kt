package org.example.project.feature.auth.domain.model

data class AuthResultData(
       val message: String,
       val accessToken: String,
       val tokenType: String
)
