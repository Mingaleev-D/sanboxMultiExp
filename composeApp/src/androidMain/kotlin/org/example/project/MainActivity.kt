package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.domain.model.EpisodeUI
import org.example.project.ui.pages.episodes.components.EpisodeRowComponent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            App()
        }
    }
}
//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}
@Preview(showBackground = true)
@Composable
private fun EpisodeRowComponentPreview() {
    val episode = EpisodeUI(
           id = 28,
           name = "The Ricklantis Mixup",
           seasonNumber = 3,
           episodeNumber = 7,
           airDate = "September 10, 2017",
           characterIdsInEpisode = listOf(
                  1,
                  2,
                  4,
                  8,
                  18,
                  22,
                  27,
                  43,
                  44,
                  48,
                  56,
                  61,
                  72,
                  73,
                  74,
                  78,
                  85,
                  86,
                  118,
                  123,
                  135,
                  143,
                  165,
                  180,
                  187,
                  206,
                  220,
                  229,
                  233,
                  235,
                  267,
                  278,
                  281,
                  283,
                  284,
                  287,
                  288,
                  289,
                  291,
                  292,
                  322,
                  325,
                  328,
                  345,
                  366,
                  367,
                  392,
                  472,
                  473,
                  474,
                  475,
                  476,
                  477,
                  478,
                  479,
                  480,
                  481,
                  482,
                  483,
                  484,
                  485,
                  486,
                  487,
                  488,
                  489
           )
    )

    EpisodeRowComponent(episode = episode)
}
