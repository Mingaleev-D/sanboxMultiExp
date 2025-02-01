package org.example.project.ui.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import org.example.project.ui.auth.components.CustomTextField
import org.example.project.ui.navigation.Routes
import org.example.project.ui.theme.ButtonHeight
import org.example.project.ui.theme.ExtraLargeSpacing
import org.example.project.ui.theme.LargeSpacing
import org.example.project.ui.theme.MediumSpacing
import org.example.project.ui.theme.SmallSpacing
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.login_button_label
import sanboxmultiexp.composeapp.generated.resources.password_hint

@Composable
fun LoginRoot(
       navController: NavController
) {
    val viewModel: LoginViewModel = koinViewModel()
    //val state = viewModel.uiState

    LoginPage(
           uiState = viewModel.uiState,
           onEmailChange = {
               viewModel.updateEmail(it)
           },
           onPasswordChange = {
               viewModel.updatePassword(it)
           },
           onNavigateToHome = {
               navController.navigate(Routes.Home){
                   launchSingleTop = true
                   popUpTo(Routes.Login) { // Удаляем Login из стека
                       inclusive = true // Включая сам Login
                   }
               }
           },
           onSignInClick = {
               viewModel.signIn()
           },
           onNavigateToSignup = {
               navController.navigate(Routes.SignUp)
           },
    )

}

@Composable
fun LoginPage(
       modifier: Modifier = Modifier,
       uiState: LoginUiState,
       onEmailChange: (String) -> Unit,
       onPasswordChange: (String) -> Unit,
       onNavigateToHome: () -> Unit,
       onSignInClick: () -> Unit,
       onNavigateToSignup: () -> Unit
) {
    LaunchedEffect(
           key1 = uiState.authenticationSuccess,
           key2 = uiState.authErrorMsg,
           block = {
               println("uiState.authenticationSuccess: ${uiState.authenticationSuccess}")

               if (uiState.authenticationSuccess) {
                   println("Authentication Success! Navigating to Home")
                   onNavigateToHome()
               }

               if (uiState.authErrorMsg != null) {
                   // Toast.makeText(context, uiState.authErrorMessage, Toast.LENGTH_SHORT).show()
               }
           }
    )

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
               modifier = modifier
                   .fillMaxSize()
                   .verticalScroll(rememberScrollState())
                   .background(
                          color = if (isSystemInDarkTheme()) {
                              MaterialTheme.colors.background
                          } else {
                              MaterialTheme.colors.surface
                          }
                   )
                   .padding(
                          top = ExtraLargeSpacing,
                          start = LargeSpacing + MediumSpacing,
                          end = LargeSpacing + MediumSpacing,
                          bottom = LargeSpacing
                   ),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            Box(
                   modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                       text = "SIgnIn",
                       modifier = Modifier
                           .align(Alignment.Center), // Центрируем текст в Box
                       textAlign = TextAlign.Center,
                       style = MaterialTheme.typography.body1.copy(
                              fontWeight = FontWeight.ExtraBold
                       )
                )
            }

            CustomTextField(
                   value = uiState.email,
                   onValueChange = onEmailChange,
                   hint = "Email",
                   keyboardType = KeyboardType.Email
            )

            CustomTextField(
                   value = uiState.password,
                   onValueChange = onPasswordChange,
                   hint = "Password",
                   keyboardType = KeyboardType.Password,
                   isPasswordTextField = true
            )

            Button(
                   onClick = {
                       onSignInClick()
                   },
                   modifier = modifier
                       .fillMaxWidth()
                       .height(ButtonHeight),
                   elevation = ButtonDefaults.elevation(
                          defaultElevation = 0.dp
                   ),
                   shape = MaterialTheme.shapes.medium
            ) {
                Text(text = stringResource(Res.string.login_button_label))
            }

            GoToSignup(modifier) {
                onNavigateToSignup()
            }
        }

        if (uiState.isAuthenticationLoading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun GoToSignup(
       modifier: Modifier = Modifier,
       onNavigateToSignup: () -> Unit
) {
    Row(
           modifier = modifier, horizontalArrangement = Arrangement.spacedBy(
           SmallSpacing
    )
    ) {
        Text(text = "Don't have an account?", style = MaterialTheme.typography.caption)
        Text(
               text = "SignUp",
               style = MaterialTheme.typography.caption,
               color = MaterialTheme.colors.primary,
               modifier = modifier.clickable { onNavigateToSignup() }
        )
    }
}
