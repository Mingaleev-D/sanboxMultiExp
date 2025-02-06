package org.example.project.ui.pages.episodes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.example.project.ui.components.LoadingState
import org.example.project.ui.pages.episodes.components.EpisodeRowComponent
import org.example.project.ui.theme.RickAction
import org.example.project.ui.theme.RickPrimary
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AllEpisodesPageRoot(
       navController: NavController
) {
    AllEpisodesPage()

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllEpisodesPage(
       viewModel: AllEpisodesPageViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.refreshAllEpisodes()
    }

    when (val state = uiState) {
        AllEpisodesUiState.Error -> {
            // todo
        }

        AllEpisodesUiState.Loading -> LoadingState()
        is AllEpisodesUiState.Success -> {
            Column {
                // SimpleToolbar(title = "All episodes")
                LazyColumn(
                       contentPadding = PaddingValues(16.dp),
                       verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    state.data.forEach { mapEntry ->
                        stickyHeader(key = mapEntry.key) {
                            Header(
                                   seasonName = mapEntry.key,
                                   uniqueCharacterCount = mapEntry.value.flatMap {
                                       it.characterIdsInEpisode
                                   }.toSet().size
                            )
                        }

                        mapEntry.value.forEach { episode ->
                            item(key = episode.id) { EpisodeRowComponent(episode = episode) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Header(
       seasonName: String,
       uniqueCharacterCount: Int
) {
    Column(
           modifier = Modifier
               .fillMaxWidth()
               .clip(RoundedCornerShape(16))
               .background(color = RickPrimary)
    ) {
        Text(
               text = seasonName,
               color = Color.White,
               fontSize = 32.sp,
               modifier = Modifier.padding(start = 6.dp),
        )
        Text(
               text = "$uniqueCharacterCount unique characters",
               color = Color.White,
               fontSize = 22.sp,
               modifier = Modifier.padding(start = 6.dp),
        )
        Spacer(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(top = 4.dp)
                   .height(4.dp)
                   .background(
                          color = RickAction,
                          shape = RoundedCornerShape(2.dp)
                   )
        )
    }
}
