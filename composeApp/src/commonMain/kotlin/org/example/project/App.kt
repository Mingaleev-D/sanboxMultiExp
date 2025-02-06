package org.example.project

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RememberMe
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.example.project.ui.navigation.NavGraphSetup
import org.example.project.ui.navigation.Routes
import org.example.project.ui.theme.RickAction
import org.example.project.ui.theme.RickPrimary
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val items = listOf(
               NavDestination.Home, NavDestination.Episodes, NavDestination.Search
        )
        var selectedIndex by remember { mutableIntStateOf(0) }

        Scaffold(
               containerColor = Color.Black,
               bottomBar = {
                   NavigationBar(
                          containerColor = RickPrimary
                   ) {
                       items.forEachIndexed { index, screen ->
                           NavigationBarItem(
                                  icon = {
                                      Icon(
                                             imageVector = screen.icon,
                                             contentDescription = null
                                      )
                                  },
                                  label = { Text(screen.title) },
                                  selected = index == selectedIndex,
                                  onClick = {
                                      selectedIndex = index
                                      navController.navigate(screen.route) {

                                          popUpTo(navController.graph.findStartDestination().id) {
                                              saveState = true
                                          }
                                          launchSingleTop = true
                                          restoreState = true
                                      }
                                  },
                                  colors = NavigationBarItemDefaults.colors(
                                         selectedIconColor = RickAction,
                                         selectedTextColor = RickAction,
                                         unselectedIconColor = Color.Gray,
                                         unselectedTextColor = Color.Gray,
                                         indicatorColor = Color.Transparent
                                  )
                           )
                       }
                   }
               }
        ) { innerPadding ->
            NavGraphSetup(
                   navHostController = navController,
                   innerPadding = innerPadding
            )
        }
    }
}

sealed class NavDestination(
       val title: String,
       val route: String,
       val icon: ImageVector
) {

    object Home : NavDestination(
           title = "Character",
           route = Routes.Home.route,
           icon = Icons.Default.RememberMe
    )

    object Episodes :
           NavDestination(
                  title = "Episodes",
                  route = Routes.Episodes.route,
                  icon = Icons.Filled.Videocam
           )

    object Search :
           NavDestination(
                  title = "Search",
                  route = Routes.Search.route,
                  icon = Icons.Filled.Search
           )
}
