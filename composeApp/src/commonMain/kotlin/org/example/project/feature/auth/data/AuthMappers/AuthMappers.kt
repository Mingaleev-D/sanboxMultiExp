package org.example.project.feature.auth.data.AuthMappers

import org.example.project.feature.auth.data.model.ResponseRequest
import org.example.project.feature.auth.domain.model.AuthResultData

fun ResponseRequest.toAuthResultData(): AuthResultData {
    return AuthResultData(
           message = this.message,
           accessToken = this.accessToken,
           tokenType = this.tokenType
    )
}
