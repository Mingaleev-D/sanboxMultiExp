package org.example.project.ui.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.feature.auth.domain.usecase.SignUpUseCase
import org.example.project.utils.SimpleResponse

class SignUpViewModel(
       private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun signUp() {
        viewModelScope.launch {
            uiState = uiState.copy(isAuthenticationLoading = true)
            val authData = signUpUseCase(
                   uiState.username,
                   uiState.email,
                   uiState.password
            )

            when (authData) {
                is SimpleResponse.Error -> {
                    uiState = uiState.copy(
                           isAuthenticationLoading = false,
                           authErrorMsg = authData.message
                    )
                }
                SimpleResponse.Loading -> {

                }
                is SimpleResponse.Success -> {
                    uiState = uiState.copy(
                           isAuthenticationLoading = false,
                           authenticationSuccess = true
                    )
                }
            }
        }
    }

    fun updateUsername(input: String) {
        uiState = uiState.copy(username = input)
    }

    fun updateEmail(input: String) {
        uiState = uiState.copy(email = input)
    }

    fun updatePassword(input: String) {
        uiState = uiState.copy(password = input)
    }
}

data class SignUpUiState(
       var username: String = "",
       var email: String = "",
       var password: String = "",
       var isAuthenticationLoading: Boolean = false,
       var authErrorMsg: String? = null,
       var authenticationSuccess: Boolean = false
)
