package org.example.project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.example.project.core.model.fake_data.AllPostDTO
import org.example.project.ui.theme.DarkGray
import org.example.project.ui.theme.ExtraLargeSpacing
import org.example.project.ui.theme.LargeSpacing
import org.example.project.ui.theme.LightGray
import org.example.project.ui.theme.MediumSpacing
import org.jetbrains.compose.resources.painterResource
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.chat_icon_outlined
import sanboxmultiexp.composeapp.generated.resources.dark_image_place_holder
import sanboxmultiexp.composeapp.generated.resources.light_image_place_holder
import sanboxmultiexp.composeapp.generated.resources.like_icon_filled
import sanboxmultiexp.composeapp.generated.resources.like_icon_outlined
import sanboxmultiexp.composeapp.generated.resources.round_more_horizontal

@Composable
fun PostListItem(
       modifier: Modifier = Modifier,
       post: AllPostDTO,
       onPostClick: (id: Int) -> Unit,
       onProfileClick: () -> Unit,
       onLikeClick: (AllPostDTO) -> Unit,
       onCommentClick: (AllPostDTO) -> Unit,
       //maxLines: Int = Int.MAX_VALUE
) {
    Column(
           modifier = modifier
               .fillMaxWidth()
               .background(color = MaterialTheme.colors.surface)
    ) {
        PostHeader(
               name = post.name ?: "",
               profileUrl = post.profilePhotoUrl ?: "",
               date = post.createdAt ?: "",
               onProfileClick = onProfileClick
        )
        AsyncImage(
               model = post.featuredimage ?: "https://services.google.com/fh/files/emails/image6_play_newsletter_march_2021.png",
               contentDescription = null,
               modifier = modifier
                   .fillMaxWidth()
                   .aspectRatio(ratio = 1.0f).clickable { onPostClick(post.id) },
               contentScale = ContentScale.Crop,
               placeholder = if (MaterialTheme.colors.isLight) {
                   painterResource(Res.drawable.light_image_place_holder)
               } else {
                   painterResource(Res.drawable.dark_image_place_holder)
               }
        )

        PostLikesRow(
               likesCount = post.like ?: 0,
               commentCount = post.views ?: 0,
               onLikeClick = { onLikeClick(post) },
               isPostLiked = false, //post.isLiked ?: false,
               onCommentClick = { onCommentClick(post) }
        )

        Text(
               text = post.title,
               style = MaterialTheme.typography.body2,
               modifier = modifier.padding(horizontal = LargeSpacing),
               maxLines = 3,
               overflow = TextOverflow.Ellipsis,
               color = MaterialTheme.colors.onBackground,
        )
    }
}

@Composable
fun PostHeader(
       modifier: Modifier = Modifier,
       name: String,
       profileUrl: String?,
       date: String,
       onProfileClick: () -> Unit,
) {
    Row(
           modifier = modifier
               .fillMaxWidth()
               .padding(
                      horizontal = LargeSpacing,
                      vertical = MediumSpacing
               ),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        CircleImage(
               modifier = modifier.size(30.dp),
               imgUrl = profileUrl ?: "https://services.google.com/fh/files/emails/image6_play_newsletter_march_2021.png",
               onClick = onProfileClick
        )

        Text(
               text = name,
               style = MaterialTheme.typography.subtitle2,
               color = MaterialTheme.colors.onSurface
        )

        Box(
               modifier = modifier
                   .size(4.dp)
                   .clip(CircleShape)
                   .background(
                          color = if (MaterialTheme.colors.isLight) {
                              LightGray
                          } else {
                              DarkGray
                          }
                   )
        )

        Text(
               text = date,
               style = MaterialTheme.typography.caption.copy(
                      textAlign = TextAlign.Start,
                      fontSize = 12.sp,
                      color = if (MaterialTheme.colors.isLight) {
                          LightGray
                      } else {
                          DarkGray
                      }
               ),
               modifier = modifier.weight(1f)
        )

        Icon(
               painter = painterResource(Res.drawable.round_more_horizontal),
               contentDescription = null,
               tint = if (MaterialTheme.colors.isLight) {
                   LightGray
               } else {
                   DarkGray
               }
        )
    }
}

@Composable
fun PostLikesRow(
       modifier: Modifier = Modifier,
       likesCount: Int,
       commentCount: Int,
       onLikeClick: () -> Unit,
       isPostLiked: Boolean,
       onCommentClick: () -> Unit
) {
    Row(
           modifier = modifier
               .fillMaxWidth()
               .padding(
                      vertical = 0.dp,
                      horizontal = MediumSpacing
               ),
           verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
               onClick = onLikeClick
        ) {
            Icon(
                   painter = if (isPostLiked) {
                       painterResource(Res.drawable.like_icon_filled)
                   } else {
                       painterResource(Res.drawable.like_icon_outlined)
                   },
                   contentDescription = null,
                   tint = if (isPostLiked) Red else DarkGray
            )
        }

        Text(
               text = "$likesCount",
               style = MaterialTheme.typography.subtitle2.copy(fontSize = 18.sp),
               color = MaterialTheme.colors.onBackground
        )

        Spacer(modifier = modifier.width(MediumSpacing))

        IconButton(
               onClick = onCommentClick
        ) {
            Icon(
                   painter = painterResource(Res.drawable.chat_icon_outlined),
                   contentDescription = null,
                   tint = if (MaterialTheme.colors.isLight) {
                       LightGray
                   } else {
                       DarkGray
                   }
            )
        }

        Text(
               text = "$commentCount",
               style = MaterialTheme.typography.subtitle2.copy(fontSize = 18.sp),
               color = MaterialTheme.colors.onBackground
        )
    }
}
