package org.example.project.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    object Login : Routes()

    @Serializable
    object SignUp : Routes()

    @Serializable
    object Home : Routes()

    @Serializable
    object PDetails : Routes()
}
