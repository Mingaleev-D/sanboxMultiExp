package org.example.project.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(
) {

    @Serializable
    object Login : Routes()

    @Serializable
    object SignUp : Routes()

    @Serializable
    object Home : Routes()

   @Serializable
    object Profile : Routes(){
        fun withId(userId: Int) = "Profile/$userId"
    }

    @Serializable
    object PDetails : Routes(){
        fun withId(postId: Int) = "PDetails/$postId"
    }
}
