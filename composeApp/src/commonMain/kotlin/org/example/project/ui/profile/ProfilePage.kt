package org.example.project.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.core.model.fake_data.AllPostDTO
import org.example.project.ui.components.CircleImage
import org.example.project.ui.components.FollowsBtn
import org.example.project.ui.components.PostListItem
import org.example.project.ui.theme.LargeSpacing
import org.example.project.ui.theme.MediumSpacing
import org.example.project.ui.theme.SmallSpacing
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.round_arrow_back

@Composable
fun ProfileRoot(
       navController: NavController,
       userId: Int
) {
    val viewModel: ProfileViewModel = koinViewModel()
    ProfilePage(
           navController = navController,
           uSerUIState = viewModel.userUIState,
           profilePostUIState = viewModel.profileUiState,
           onBtnClick = {},
           onFollowersClick = {},
           onFollowingClick = {},
           onPostClick = {},
           onLikeClick = {},
           onCommentClick = {},
           fetchData = {
               viewModel.fetchProfile(userId)
           },
    )
}

@Composable
fun ProfilePage(
       modifier: Modifier = Modifier,
       navController: NavController,
       uSerUIState: USerUIState,
       profilePostUIState: ProfilePostUIState,
       onBtnClick: () -> Unit,
       onFollowersClick: () -> Unit,
       onFollowingClick: () -> Unit,
       onPostClick: (AllPostDTO) -> Unit,
       onLikeClick: (String) -> Unit,
       onCommentClick: (String) -> Unit,
       fetchData: () -> Unit
) {
    LaunchedEffect(key1 = Unit) { fetchData() }

    Column(
           modifier = modifier.fillMaxSize(),
    ) {
        Row(
               modifier = Modifier.padding(start = 6.dp, end = 10.dp),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                   onClick = {
                       navController.popBackStack()
                   }
            ) {
                Icon(
                       painter = painterResource(Res.drawable.round_arrow_back),
                       contentDescription = null,
                       tint = MaterialTheme.colors.onSurface
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                   text = "Profile",
                   style = MaterialTheme.typography.body1.copy(
                          fontFamily = FontFamily.Monospace,
                          fontSize = 16.sp,
                          fontWeight = FontWeight.Bold
                   ),
                   color = MaterialTheme.colors.onSurface,
                   maxLines = 1
            )
        }
        // All code
        if (uSerUIState.isLoading && profilePostUIState.isLoading) {
            Box(
                   modifier = Modifier.fillMaxSize(),
                   contentAlignment = Alignment.Center,
                   content = { CircularProgressIndicator(strokeWidth = 2.dp) }
            )
        } else {
            LazyColumn(
                   modifier = Modifier.fillMaxSize(),
            ) {
                item(
                       key = "header_selection"
                ) {
                    ProfileHeaderSelection(
                           imageUrl = uSerUIState.profile?.profileImgUrl,
                           name = uSerUIState.profile?.name,
                           bio = uSerUIState.profile?.bio,
                           followersCount = uSerUIState.profile?.followersCount,
                           followingCount = uSerUIState.profile?.followingCount,
                           onBtnClick = { onBtnClick() },
                           onFollowersClick = { onFollowersClick() },
                           onFollowingClick = { onFollowingClick() },
                    )
                }

                items(
                       items = profilePostUIState.post,
                       key = { post -> post.id }
                ) { post ->
                    PostListItem(
                           post = post,
                           onPostClick = { onPostClick(post) },
                           onProfileClick = {},
                           onLikeClick = { onLikeClick(post.id.toString()) },
                           onCommentClick = { onCommentClick(post.id.toString()) }
                    )
                }
            }

        }
    }

}

@Composable
fun ProfileHeaderSelection(
       modifier: Modifier = Modifier,
       imageUrl: String? = null,
       name: String? = null,
       bio: String? = null,
       followersCount: Int? = 0,
       followingCount: Int? = 0,
       isCurrentUser: Boolean = false,
       isFollowing: Boolean = false,
       onBtnClick: () -> Unit,
       onFollowersClick: () -> Unit,
       onFollowingClick: () -> Unit
) {
    Column(
           modifier = modifier
               .fillMaxWidth()
               .padding(bottom = MediumSpacing)
               .background(color = MaterialTheme.colors.surface).padding(LargeSpacing),
    ) {
        CircleImage(
               modifier = Modifier.size(90.dp),
               imgUrl = imageUrl ?: "https://services.google.com/fh/files/emails/image6_play_newsletter_march_2021.png",
               onClick = { }
        )
        Spacer(modifier = Modifier.height(SmallSpacing))

        Text(
               text = name ?: "",
               style = MaterialTheme.typography.subtitle1,
               color = MaterialTheme.colors.onBackground,
               maxLines = 2,
               overflow = TextOverflow.Ellipsis
        )
        Text(
               text = bio ?: "",
               style = MaterialTheme.typography.body2,
               color = MaterialTheme.colors.onBackground,
               maxLines = 2,
               overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(MediumSpacing))

        Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceEvenly,
               verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                   //modifier = Modifier.weight(1f),
            ) {
                FollowsText(
                       count = followersCount ?: 0,
                       text = "Followers",
                       onClick = { onFollowersClick() }
                )

                Spacer(modifier = Modifier.width(MediumSpacing))

                FollowsText(
                       count = followingCount ?: 0,
                       text = "Following",
                       onClick = { onFollowingClick() }
                )
            }

            FollowsBtn(
                   text = "Follow",
                   onClick = { onBtnClick() },
                   modifier = Modifier.width(90.dp),
            )
        }
    }

}

@Composable
fun FollowsText(
       modifier: Modifier = Modifier,
       count: Int,
       text: String,
       onClick: (() -> Unit)? = null,
) {
    Text(
           text = "$count $text",
           style = MaterialTheme.typography.subtitle2,
           color = MaterialTheme.colors.onSurface,
           modifier = modifier.clickable { onClick?.invoke() }
    )
    //    Text(
    //           text = buildAnnotatedString {
    //               withStyle(
    //                      style = SpanStyle(
    //                             color = MaterialTheme.colors.onSurface,
    //                             fontWeight = FontWeight.Bold,
    //                             fontSize = 14.sp
    //                      )
    //               ) {
    //                   append(text = "$count ")
    //               }
    //               withStyle(
    //                      style = SpanStyle(
    //                             color = MaterialTheme.colors.onSurface.copy(alpha = .54f),
    //                             fontSize = 14.sp
    //                      )
    //               ) {
    //                   append(text = "$text")
    //               }
    //           },
    //           modifier = modifier.clickable { onClick() },
    //    )

}
