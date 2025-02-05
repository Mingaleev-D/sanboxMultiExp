package org.example.project.ui.pages.episodes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.domain.model.CharacterItemUI
import org.example.project.domain.model.EpisodeUI
import org.example.project.domain.repository.CharacterRepository
import org.example.project.ui.components.CharacterImage
import org.example.project.ui.components.CharacterNameComponent
import org.example.project.ui.components.DataPoint
import org.example.project.ui.components.DataPointComponent
import org.example.project.ui.components.LoadingState
import org.example.project.ui.pages.episodes.components.EpisodeRowComponent
import org.example.project.ui.theme.RickPrimary
import org.example.project.ui.theme.RickTextPrimary
import org.example.project.utils.SimpleResponse
import org.example.project.utils.onError
import org.example.project.utils.onSuccess
import org.koin.compose.koinInject

@Composable
fun EpisodesRoot(
       navController: NavController,
       characterID: Int
) {
    val characterRepo = koinInject<CharacterRepository>()
    EpisodesPage(
           navController = navController,
           characterRepository = characterRepo,
           characterID = characterID
    )

}

@Composable
fun EpisodesPage(
       navController: NavController,
       characterRepository: CharacterRepository,
       characterID: Int
) {
    var characterState by remember { mutableStateOf<SimpleResponse<CharacterItemUI>>(SimpleResponse.Loading) }
    var episodesState by remember { mutableStateOf<SimpleResponse<List<EpisodeUI>>>(SimpleResponse.Loading) }

    LaunchedEffect(
           key1 = Unit
    ) {
        characterRepository.getCharacterBuId(characterID)
            .onError { characterState = SimpleResponse.Error(it) }
            .onSuccess { character ->
                characterState = SimpleResponse.Success(character)
                characterRepository.getEpisodes(character.episodeIds)
                    .onSuccess { episodesState = SimpleResponse.Success(it) }
                    .onError { episodesState = SimpleResponse.Error(it) }
            }
    }

    when (characterState) {
        is SimpleResponse.Loading -> LoadingState()
        is SimpleResponse.Error -> (characterState as SimpleResponse.Error).message
        is SimpleResponse.Success -> {
            val character = (characterState as SimpleResponse.Success).data
            when (episodesState) {
                is SimpleResponse.Loading -> LoadingState()
                is SimpleResponse.Error -> (episodesState as SimpleResponse.Error).message
                is SimpleResponse.Success -> {
                    val episodes = (episodesState as SimpleResponse.Success).data
                    MainScreen(
                           character = character,
                           episodes = episodes,
                           onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MainScreen(
       character: CharacterItemUI,
       episodes: List<EpisodeUI>,
       onBack: () -> Unit
) {
    val episodeBySeasonMap = episodes.groupBy { it.seasonNumber }

    LazyColumn(
           contentPadding = PaddingValues(all = 16.dp),
           modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row {
                Icon(
                       imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                       contentDescription = null,
                       tint = Color.White,
                       modifier = Modifier
                           .padding(bottom = 8.dp)
                           .size(24.dp)
                           .clickable { onBack() },
                )
            }
        }
        item { CharacterNameComponent(name = character.name) }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            LazyRow {
                episodeBySeasonMap.forEach { mapEntry ->
                    val title = "Season ${mapEntry.key}"
                    val description = "${mapEntry.value.size} ep"
                    item {
                        DataPointComponent(dataPoint = DataPoint(title, description))
                        Spacer(modifier = Modifier.width(32.dp))
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { CharacterImage(imageUrl = character.imageUrl) }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        episodes.groupBy { it.seasonNumber }.forEach { mapEntry ->
            stickyHeader { SeasonHeader(seasonNumber = mapEntry.key) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            items(mapEntry.value) { episode ->
                EpisodeRowComponent(episode = episode)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun SeasonHeader(seasonNumber: Int) {
    Row(
           modifier = Modifier
               .fillMaxWidth()
               .clip(RoundedCornerShape(10.dp))
               .background(color = RickPrimary)
               .padding(top = 10.dp, bottom = 10.dp)
    ) {
        Text(
               text = "Season $seasonNumber",
               color = RickTextPrimary,
               fontSize = 32.sp,
               lineHeight = 32.sp,
               textAlign = TextAlign.Center,
               modifier = Modifier
                   .fillMaxWidth()
                   .border(
                          width = 1.dp,
                          color = RickTextPrimary,
                          shape = RoundedCornerShape(8.dp)
                   )
                   .padding(vertical = 4.dp)
        )
    }
}
