package org.example.project.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.core.model.fake_data.samplePosts
import org.example.project.ui.auth.login.LoginRoot
import org.example.project.ui.auth.signup.SingUpRoot
import org.example.project.ui.home.HomeRoot
import org.example.project.ui.post.PostDetailRoot

@Composable
fun NavGraphSetup(
       navController: NavHostController,
) {
    NavHost(
           navController = navController,
           startDestination = Routes.Home
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
            HomeRoot(
                   navController = navController
            )
        }
        composable<Routes.PDetails> {
            PostDetailRoot(
                   navController = navController,
                   // post = samplePosts[0]
            )
        }
    }
}
