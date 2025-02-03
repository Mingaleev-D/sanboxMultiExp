package org.example.project.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.ui.auth.login.LoginRoot
import org.example.project.ui.auth.signup.SingUpRoot
import org.example.project.ui.home.HomeRoot
import org.example.project.ui.post.PostDetailRoot
import org.example.project.ui.profile.ProfileRoot

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
        composable(
               route = "PDetails/{postId}"
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")?.toIntOrNull()
            if (postId != null) {
                PostDetailRoot(navController = navController, postId = postId)
            }
        }

        composable(
               route = "Profile/{userId}"
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            if (userId != null) {
                ProfileRoot(
                       navController = navController,
                       userId = userId
                )
            }
        }
    }
}
