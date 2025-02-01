package org.example.project.ui.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.feature.auth.domain.usecase.SignInUseCase
import org.example.project.utils.SimpleResponse

class LoginViewModel(
       private val signInUseCase: SignInUseCase
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun signIn(){
        viewModelScope.launch {
            uiState = uiState.copy(isAuthenticationLoading = true)
            val authData = signInUseCase(uiState.email, uiState.password)
            when (authData) {
                is SimpleResponse.Error -> {
                    uiState = uiState.copy(
                        isAuthenticationLoading = false,
                        authErrorMsg = authData.message
                    )
                }
                SimpleResponse.Loading ->{

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

    fun updateEmail(input: String) {
        uiState = uiState.copy(email = input)
    }

    fun updatePassword(input: String) {
        uiState = uiState.copy(password = input)
    }
}

data class LoginUiState(
       var email: String = "",
       var password: String = "",
       var isAuthenticationLoading: Boolean = false,
       var authErrorMsg: String? = null,
       var authenticationSuccess: Boolean = false
)
