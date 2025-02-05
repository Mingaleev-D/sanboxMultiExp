package org.example.project.ui.pages.details

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.SubcomposeAsyncImage
import org.example.project.ui.components.DataPointComponent
import org.example.project.ui.components.LoadingState
import org.example.project.ui.navigation.Routes
import org.example.project.ui.pages.details.components.CharacterDetailsNamePlateComponent
import org.example.project.ui.theme.RickAction
import org.koin.compose.koinInject

@Composable
fun CharacterDetailsRoot(
       navController: NavController,
       characterId: Int
) {
    CharacterDetailsPage(
           navController = navController,
           characterId = characterId
    )
}

@Composable
fun CharacterDetailsPage(
       navController: NavController,
       viewModel: DetailsViewModel = koinInject(),
       characterId: Int
) {
    val state by viewModel.internalStoreFlow.collectAsStateWithLifecycle()

    LaunchedEffect(
           key1 = Unit,
           block = { viewModel.fetchCharacterDetails(characterId = characterId) }
    )

    LazyColumn(
           modifier = Modifier.fillMaxSize(),
           contentPadding = PaddingValues(all = 16.dp)
    ) {
        when (state) {
            is CharacterDetailsState.Error -> TODO()
            CharacterDetailsState.Loading -> item { LoadingState() }
            is CharacterDetailsState.Success -> {
                val character = (state as CharacterDetailsState.Success).character
                val characterDataPoints = (state as CharacterDetailsState.Success).characterDataPoint
                //
                // Name plate
                item {
                    CharacterDetailsNamePlateComponent(
                           name = character.name,
                           status = character.status
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                //
                // Image
                item {
                    SubcomposeAsyncImage(
                           model = character.imageUrl,
                           contentDescription = "Character image",
                           modifier = Modifier
                               .fillMaxWidth()
                               .aspectRatio(1f)
                               .clip(RoundedCornerShape(12.dp)),
                           loading = { LoadingState() }
                    )
                }
                // Data points
                items(characterDataPoints) {
                    Spacer(modifier = Modifier.height(32.dp))
                    DataPointComponent(dataPoint = it)
                }

                item { Spacer(modifier = Modifier.height(32.dp)) }
                // Button
                item {
                    Text(
                           text = "View all episodes",
                           color = RickAction,
                           fontSize = 18.sp,
                           textAlign = TextAlign.Center,
                           modifier = Modifier
                               .padding(horizontal = 32.dp)
                               .border(
                                      width = 1.dp,
                                      color = RickAction,
                                      shape = RoundedCornerShape(12.dp)
                               )
                               .clip(RoundedCornerShape(12.dp))
                               .clickable {
                                   navController.navigate(Routes.Episodes.withId(characterId))
                               }
                               .padding(vertical = 8.dp)
                               .fillMaxWidth()
                    )
                }

                item { Spacer(modifier = Modifier.height(34.dp)) }
            }
        }
    }
}
