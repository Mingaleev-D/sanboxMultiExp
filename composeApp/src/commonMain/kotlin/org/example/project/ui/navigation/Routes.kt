package org.example.project.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(
       val route: String
) {

    @Serializable
    object Home : Routes("home"){
    }
    @Serializable
    object AllEpisodes : Routes("all_episodes"){
    }

    @Serializable
    object Episodes : Routes("episode"){
        fun withId(characterId: Int) = "${Episodes.route}/$characterId"
    }
    @Serializable
    object Search : Routes("search")

    @Serializable
    object Details : Routes("details") {

        fun withId(characterId: Int) = "${Details.route}/$characterId"
    }
}
