package org.example.project.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.ui.auth.login.LoginRoot
import org.example.project.ui.auth.signup.SingUpRoot
import org.example.project.ui.home.HomePage

@Composable
fun HavGraphSetup(
       navController: NavHostController,
) {
    NavHost(
           navController = navController,
           startDestination = Routes.Login
    ) {
        composable<Routes.Login> {
            LoginRoot(
                   navController = navController
            )
        }
        composable<Routes.SignUp> {
            SingUpRoot(
                   navController = navController,
            )
        }
        composable<Routes.Home> {
            HomePage(
            )
        }
    }
}
