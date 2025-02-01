package org.example.project.ui.auth.signup

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
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.example.project.ui.auth.components.CustomTextField
import org.example.project.ui.navigation.Routes
import org.example.project.ui.theme.ButtonHeight
import org.example.project.ui.theme.ExtraLargeSpacing
import org.example.project.ui.theme.LargeSpacing
import org.example.project.ui.theme.MediumSpacing
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.round_arrow_back
import sanboxmultiexp.composeapp.generated.resources.signup_button_hint

@Composable
fun SingUpRoot(
       navController: NavController
) {
    val viewModel: SignUpViewModel = koinViewModel()

    SignUpPage(
           uiState = viewModel.uiState,
           onUsernameChange = {
               viewModel.updateUsername(it)
           },
           onEmailChange = {
               viewModel.updateEmail(it)
           },
           onPasswordChange = {
               viewModel.updatePassword(it)
           },
           onNavigateToLogin = {
                navController.popBackStack()
           },
           onNavigationToHome = {
               navController.navigate(Routes.Home)
           },
           onSignUpClick = {
               viewModel.signUp()
           }
    )

}

@Composable
fun SignUpPage(
       modifier: Modifier = Modifier,
       uiState: SignUpUiState,
       onUsernameChange: (String) -> Unit,
       onEmailChange: (String) -> Unit,
       onPasswordChange: (String) -> Unit,
       onNavigateToLogin: () -> Unit,
       onNavigationToHome: () -> Unit,
       onSignUpClick: () -> Unit
) {
    LaunchedEffect(
           key1 = uiState.authenticationSuccess,
           key2 = uiState.authErrorMsg,
           block = {
               if (uiState.authenticationSuccess) {
                   onNavigationToHome()
               }
               if (uiState.authErrorMsg != null) {
                   // toast

               }
           }
    )

    Box(
           modifier = modifier.fillMaxSize(),
           contentAlignment = Alignment.Center
    ) {
        Column(
               modifier = Modifier
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
                   modifier = Modifier
                       .fillMaxWidth()
            ) {
                Icon(
                       imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                       contentDescription = null,
                       modifier = Modifier
                           .align(Alignment.CenterStart).clickable { onNavigateToLogin() }
                       //.padding(start = 16.dp)
                )

                Text(
                       text = "SignUp",
                       modifier = Modifier
                           .align(Alignment.Center), // Центрируем текст в Box
                       textAlign = TextAlign.Center,
                       style = MaterialTheme.typography.body1.copy(
                              fontWeight = FontWeight.ExtraBold
                       )
                )
            }
            CustomTextField(
                   value = uiState.username,
                   onValueChange = onUsernameChange,
                   hint = "Username",
            )

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
                       onSignUpClick()
                   },
                   modifier = modifier
                       .fillMaxWidth()
                       .height(ButtonHeight),
                   elevation = ButtonDefaults.elevation(
                          defaultElevation = 0.dp
                   ),
                   shape = MaterialTheme.shapes.medium
            ) {
                Text(text = stringResource(Res.string.signup_button_hint))
            }
        }

        if (uiState.isAuthenticationLoading) {
            CircularProgressIndicator(strokeWidth = 4.dp)
        }
    }

}
