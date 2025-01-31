package org.example.project.ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import org.example.project.ui.components.NavigationType
import org.example.project.ui.components.SearchLocationContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomePage(
       modifier: Modifier = Modifier,
       navigationType: NavigationType,
       homeViewModel: HomeViewModel = koinViewModel()
) {

    val state by homeViewModel.homeState.collectAsStateWithLifecycle()
    val (search, onSearchChange) = remember { mutableStateOf("") }
    val context = LocalPlatformContext.current
    var showSearchLocation by rememberSaveable { mutableStateOf(false) }

    AnimatedVisibility(visible =  showSearchLocation || !state.isLocationSelected, modifier = modifier){
        SearchLocationContent(
               modifier = modifier,
               state = state,
               search = search,
               onSearchChange = onSearchChange,
               onSubmit = { homeViewModel.fetchGeoLocation(search) },
               context = context,
               navigationType = navigationType,
               onFavouriteClick = {
                   homeViewModel.setSelectedLocation(it)
                   homeViewModel.saveFavouriteLocation()
                   homeViewModel.getGeoLocation()
                   showSearchLocation = !showSearchLocation
               },
               onNavigateBack = {
                   showSearchLocation = false
               }
        )

    }

//    AnimatedVisibility(!showSearchLocation, modifier = modifier) {
//        if (state.isLocationSelected) {
//            ForecastScreen(
//                   modifier = modifier,
//                   onSearchClick = {
//                       showSearchLocation = !showSearchLocation
//                   }
//            )
//        } else {
//            Text("Please Select a location to get started")
//        }
//    }


}
