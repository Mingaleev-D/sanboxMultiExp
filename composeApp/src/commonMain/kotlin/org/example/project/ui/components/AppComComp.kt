package org.example.project.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.example.project.ui.navigation.Routes
import org.jetbrains.compose.resources.painterResource
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.person_circle_icon

@Composable
fun AppComComp(
       modifier: Modifier = Modifier,
       navController: NavController
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Surface(
           modifier = modifier,
           elevation = 4.dp
    ) {
    }
    TopAppBar(
           title = {
               Text(
                      text = getAppBarTitle(currentRoute),
                      style = MaterialTheme.typography.body1.copy(
                             fontWeight = FontWeight.ExtraBold
                      )
               )
           },
           backgroundColor = MaterialTheme.colors.surface,
           actions = {
               IconButton(
                      onClick = {
                      }
               ) {
                   Icon(
                          painter = painterResource(Res.drawable.person_circle_icon),
                          contentDescription = null
                   )
               }
           },

           modifier = Modifier,
    )

}

private fun getAppBarTitle(currentRoute: String?): String {
    when (currentRoute) {
        Routes.Home.toString() -> return "github.com/Mingaleev-D"
        Routes.Login.toString() -> return "Login"
        Routes.SignUp.toString() -> return "SignUp"
        else -> return ""
    }
}

private fun shouldShowNavigationIcon(currentRoute: String?): Boolean {
    return when (currentRoute) {
        Routes.Home.toString() -> true
        else -> false
    }
}
