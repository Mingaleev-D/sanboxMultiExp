package org.example.project.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.ui.pages.details.CharacterDetailsRoot
import org.example.project.ui.pages.episodes.EpisodesRoot
import org.example.project.ui.pages.home.HomeRoot

@Composable
fun NavGraphSetup(
       navHostController: NavHostController
) {
    NavHost(
           navController = navHostController,
           startDestination = Routes.Home.route
    ) {
        composable(route = Routes.Home.route) {
            HomeRoot(navController = navHostController)
        }

        composable(
               route = "${Routes.Details.route}/{characterId}",
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            if (characterId != null) {
                CharacterDetailsRoot(
                       navController = navHostController,
                       characterId = characterId
                )
            }
        }
        composable(
               route = "${Routes.Episodes.route}/{characterId}"
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            if (characterId != null) {
                EpisodesRoot(
                       navController = navHostController,
                       characterID = characterId
                )
            }
        }
    }

}
