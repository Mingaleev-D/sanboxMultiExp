package org.example.project.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.core.model.fake_data.AllPostDTO
import org.example.project.core.model.fake_data.FollowsUser
import org.example.project.ui.components.PostListItem
import org.example.project.ui.home.components.OnBoardingSection
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.person_circle_icon

@Composable
fun HomeRoot(
       navController: NavController
) {
    val viewModel: HomeViewModel = koinViewModel()
    HomePage(
           postUIState = viewModel.postUIState,
           onBoardingUIState = viewModel.onBoardingUIState,
           // post = TODO(),
           onPostClick = { },
           onProfileClick = {},
           onLikeClick = {},
           onCommentClick = {},
           onFollowButtonClick = { _, _ -> },
           onBoardingFinish = {},
           fetchMoreData = {
               viewModel.fetchData()
           }
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomePage(
       modifier: Modifier = Modifier,
       postUIState: PostUIState,
       onBoardingUIState: OnBoardingUIState,
       // post: AllPostDTO,
       onPostClick: (AllPostDTO) -> Unit,
       onProfileClick: () -> Unit,
       onLikeClick: () -> Unit,
       onCommentClick: () -> Unit,
       onFollowButtonClick: (Boolean, FollowsUser) -> Unit,
       onBoardingFinish: () -> Unit,
       fetchMoreData: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
           refreshing = onBoardingUIState.isLoading || postUIState.isLoading,
           onRefresh = fetchMoreData,
    )
    val listState = rememberLazyListState()

    Box(
           modifier = Modifier.fillMaxSize(),
    ) {
        PullRefreshIndicator(
               refreshing = onBoardingUIState.isLoading || postUIState.isLoading,
               state = pullRefreshState,
               modifier = Modifier.align(
                      alignment = Alignment.Center
               )
        )
    }

    Column(
           modifier = Modifier
               .fillMaxSize()
               .pullRefresh(state = pullRefreshState),
    ) {
        Row(
               modifier = Modifier.padding(start = 26.dp, end = 10.dp),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center
        ) {
            Text(
                   text = "github.com/Mingaleev-D",
                   style = MaterialTheme.typography.body1.copy(
                          fontFamily = FontFamily.Monospace,
                          fontSize = 16.sp,
                          fontWeight = FontWeight.Bold
                   ),
                   color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                   onClick = {
                   }
            ) {
                Icon(
                       painter = painterResource(Res.drawable.person_circle_icon),
                       contentDescription = null
                )
            }
        }
        // Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
               modifier = Modifier.fillMaxSize(),
               state = listState
        ) {
            if (onBoardingUIState.shouldShowOnBoarding) {
                item(key = "onboardingSelectionKey") {
                    OnBoardingSection(
                           users = onBoardingUIState.users,
                           onUserClick = onProfileClick,
                           onFollowButtonClick = onFollowButtonClick,
                           onBoardingFinish = onBoardingFinish
                    )
                }
            }


            items(
                   items = postUIState.postList,
                   key = { post -> post.id }
            ) { items ->
                PostListItem(
                       post = items,
                       onPostClick = onPostClick,
                       onProfileClick = {},
                       onLikeClick = {
                       },
                       onCommentClick = {}
                )
            }
        }
    }

}
