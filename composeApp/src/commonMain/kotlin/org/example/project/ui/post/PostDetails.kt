package org.example.project.ui.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.core.model.fake_data.AllPostDTO
import org.example.project.core.model.fake_data.Comments
import org.example.project.core.model.fake_data.sampleComments
import org.example.project.ui.components.CommentListItem
import org.example.project.ui.components.PostListItem
import org.example.project.ui.theme.ButtonHeight
import org.example.project.ui.theme.Gray
import org.example.project.ui.theme.LargeSpacing
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.add_comment_button_label
import sanboxmultiexp.composeapp.generated.resources.comments_label
import sanboxmultiexp.composeapp.generated.resources.login_button_label
import sanboxmultiexp.composeapp.generated.resources.person_circle_icon
import sanboxmultiexp.composeapp.generated.resources.round_arrow_back

@Composable
fun PostDetailRoot(
       navController: NavController,
       postId: Int
) {
    val viewModel: PostDetailsViewModel = koinViewModel()
    // Загружаем данные поста
    LaunchedEffect(
           postId
    ) {
        viewModel.fetchData(postId)
    }

    PostDetails(
           navController = navController,
           posyUIState = viewModel.postUIState,
           commentUIState = viewModel.commentUIState,
           onCommentMoreIconClick = {},
           onProfileClick = {},
           onAddCommentsClick = {}
    )

}

@Composable
fun PostDetails(
       modifier: Modifier = Modifier,
       navController: NavController,
       posyUIState: PosyUIState,
       commentUIState: CommentUIState,
       onCommentMoreIconClick: (Comments) -> Unit,
       onProfileClick: (Int) -> Unit,
       onAddCommentsClick: () -> Unit
) {
    val postName = posyUIState.post?.name ?: ""

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
                   text = postName,
                   style = MaterialTheme.typography.body1.copy(
                          fontFamily = FontFamily.Monospace,
                          fontSize = 16.sp,
                          fontWeight = FontWeight.Bold
                   ),
                   color = MaterialTheme.colors.onSurface,
                   maxLines = 1
            )
        }
        if (posyUIState.isLoading && commentUIState.isLoading) {
            Box(
                   modifier = Modifier.fillMaxSize(),
                   contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            }
        } else if (posyUIState.post != null) {
            LazyColumn(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(color = MaterialTheme.colors.surface),
            ) {
                item(
                       key = "post_item"
                ) {
                    PostListItem(
                           post = posyUIState.post,
                           onPostClick = {},
                           onProfileClick = {},
                           onLikeClick = {},
                           onCommentClick = {
                           },
                    )
                }
                item(key = "comments_header") {
                    CommentSectionHeader(
                           onAddCommentClick = {}
                    )
                }

                items(
                       items = sampleComments,
                       key = { comment -> comment.id }
                ) { comment ->
                    Divider()
                    CommentListItem(
                           comment = comment,
                           onProfileClick = {},
                           onMoreIconClick = {}
                    )
                }
            }
        } else if (posyUIState.post == null) {
            Box(
                   modifier = Modifier.fillMaxSize(),
                   contentAlignment = Alignment.Center
            ) {
                Text(
                       text = "Post not found",
                       style = MaterialTheme.typography.caption
                )
            }

        }
    }

}

@Composable
fun CommentSectionHeader(
       modifier: Modifier = Modifier,
       onAddCommentClick: () -> Unit,
) {
    Row(
           modifier = modifier
               .fillMaxWidth()
               .padding(LargeSpacing),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
               text = stringResource(Res.string.comments_label),
               style = MaterialTheme.typography.subtitle1,
               color = MaterialTheme.colors.onBackground
        )
        Button(
               onClick = {
                   onAddCommentClick()
               },
               elevation = ButtonDefaults.elevation(
                      defaultElevation = 1.dp
               ),
               shape = MaterialTheme.shapes.medium
        ) {
            Text(
                   text = stringResource(Res.string.add_comment_button_label),
                   color = if (MaterialTheme.colors.isLight) Color.White else Gray
            )
        }
    }

}
