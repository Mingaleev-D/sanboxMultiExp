package org.example.project.ui.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.example.project.ui.components.LoadingState
import org.example.project.ui.navigation.Routes
import org.example.project.ui.pages.home.components.CharacterGridItem
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoot(
       navController: NavController
) {
    HomePage(
           onCharacterSelected = { characterId ->
               navController.navigate("${Routes.Details.route}/$characterId")
           }
    )

}

@Composable
fun HomePage(
       modifier: Modifier = Modifier,
       viewModel: HomeViewModel = koinViewModel(),
       onCharacterSelected: (Int) -> Unit
) {
    val viewState by viewModel.homeState.collectAsStateWithLifecycle()

    LaunchedEffect(
           key1 = viewModel,
           block = { viewModel.fetchInitPage() }
    )
    val scrollState = rememberLazyGridState()
    val fetchNextPage: Boolean by remember {
        derivedStateOf {
            val currentCharacterCount = (viewState as? HomeUIState.GridDisplay)?.characters?.size ?: return@derivedStateOf false
            val lastDisplayedIndex = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return@derivedStateOf false
            // Загружаем, если близко к концу ИЛИ если список короткий (например, начальная загрузка)
            return@derivedStateOf lastDisplayedIndex >= currentCharacterCount - 5 || currentCharacterCount < 10
        }
    }

    LaunchedEffect(
           key1 = fetchNextPage,
           block = {
               if (fetchNextPage) {
                   viewModel.getNextPage()
               }
           }
    )
    when (val state = viewState) {
        is HomeUIState.Error -> state.message
        is HomeUIState.GridDisplay -> {
            LazyVerticalGrid(
                   state = scrollState,
                   columns = GridCells.Fixed(2),
                   contentPadding = PaddingValues(all = 10.dp),
                   verticalArrangement = Arrangement.spacedBy(8.dp),
                   horizontalArrangement = Arrangement.spacedBy(8.dp),
                   content = {
                       items(
                              items = state.characters,
                              key = { it.id }
                       ) { character ->
                           CharacterGridItem(
                                  modifier = Modifier,
                                  character = character,
                                  onClick = { onCharacterSelected(character.id) }
                           )
                       }
                   }
            )
        }

        HomeUIState.Loading -> LoadingState()
    }

}
