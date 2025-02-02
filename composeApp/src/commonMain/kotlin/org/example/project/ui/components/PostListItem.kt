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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.example.project.core.model.fake_data.AllPostDTO
import org.example.project.core.model.fake_data.PopularPostItemDTO
import org.example.project.ui.theme.DarkGray
import org.example.project.ui.theme.LargeSpacing
import org.example.project.ui.theme.LightGray
import org.example.project.ui.theme.MediumSpacing
import org.jetbrains.compose.resources.painterResource
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.chat_icon_outlined
import sanboxmultiexp.composeapp.generated.resources.dark_image_place_holder
import sanboxmultiexp.composeapp.generated.resources.light_image_place_holder
import sanboxmultiexp.composeapp.generated.resources.like_icon_outlined
import sanboxmultiexp.composeapp.generated.resources.round_more_horiz_24

@Composable
fun PostListItem(
       modifier: Modifier = Modifier,
       post: AllPostDTO,
       onPostClick: (AllPostDTO) -> Unit,
       onProfileClick: () -> Unit,
       onLikeClick: () -> Unit,
       onCommentClick: () -> Unit,
       isDetailsScreen: Boolean = false
) {
    Column(
           modifier = modifier
               .fillMaxWidth()
               .aspectRatio(ratio = .7f)
               .background(color = MaterialTheme.colors.surface)
               .clickable {
                   onPostClick(post)
               },
    ) {
        // todo проработать передачу данных внутри компонента открытие деталей поста
        // передавать обьект поста!
        PostItemHeader(
               name = post.name ?: "No Name",
               profileUrl = post.profilePhotoUrl ?: "",
               date = post.createdAt ?: "Unknown",
               onProfileClick = {
                   onProfileClick()
               }
        )

        AsyncImage(
               model = post.featuredimage ?: "https://services.google.com/fh/files/emails/image6_play_newsletter_march_2021.png",
               contentDescription = null,
               modifier = Modifier.fillMaxWidth().aspectRatio(ratio = 1.0f),
               contentScale = ContentScale.Crop,
               placeholder = if (MaterialTheme.colors.isLight) {
                   painterResource(Res.drawable.light_image_place_holder)
               } else {
                   painterResource(Res.drawable.dark_image_place_holder)
               },
        )

        PostLikesRow(
               likesCount = post.like,
               commentsCount = post.views,
               onLikeClick = onLikeClick,
               onCommentClick = onCommentClick
        )

        Text(
               text = post.title,
               style = MaterialTheme.typography.body2,
               modifier = Modifier.padding(horizontal = LargeSpacing),
               maxLines = if (isDetailsScreen) 20 else 2,
               overflow = TextOverflow.Ellipsis
        )
    }

}

@Composable
fun PostItemHeader(
       modifier: Modifier = Modifier,
       name: String?,
       profileUrl: String?,
       date: String?,
       onProfileClick: () -> Unit
) {
    Row(
           modifier = modifier
               .fillMaxWidth()
               .padding(
                      horizontal = LargeSpacing,
                      vertical = MediumSpacing
               ),
           verticalAlignment = CenterVertically,
           horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
//        CircleImage(
//               modifier = Modifier.size(30.dp),
//               imgUrl = profileUrl ?: "https://services.google.com/fh/files/emails/image6_play_newsletter_march_2021.png",
//               onClick = onProfileClick
//        )

//        Text(
//               text = name ?: "no data",
//               style = MaterialTheme.typography.subtitle2,
//               color = MaterialTheme.colors.onSurface
//        )
        Box(
               modifier = Modifier
                   .size(4.dp)
                   .clip(CircleShape)
                   .background(
                          color = if (MaterialTheme.colors.isLight) {
                              LightGray
                          } else {
                              DarkGray
                          }
                   ),
        )
//        Text(
//               text = date ?: "no data",
//               style = MaterialTheme.typography.caption.copy(
//                      textAlign = TextAlign.Center,
//                      fontSize = 12.sp
//               ),
//               color = if (MaterialTheme.colors.isLight) {
//                   LightGray
//               } else {
//                   DarkGray
//               },
//               modifier = Modifier.weight(1f),
//        )
        Icon(
               painter = painterResource(Res.drawable.round_more_horiz_24),
               contentDescription = null,
               tint = if (MaterialTheme.colors.isLight) {
                   LightGray
               } else {
                   DarkGray
               },
        )
    }

}

@Composable
fun PostLikesRow(
       modifier: Modifier = Modifier,
       likesCount: Int?,
       commentsCount: Int?,
       onLikeClick: () -> Unit,
       onCommentClick: () -> Unit
) {
    Row(
           modifier = modifier.fillMaxWidth().padding(
                  vertical = 0.dp,
                  horizontal = MediumSpacing
           ),
           verticalAlignment = CenterVertically
    ) {
        IconButton(
               onClick = onLikeClick
        ) {
            Icon(
                   painter = painterResource(Res.drawable.like_icon_outlined),
                   contentDescription = null,
                   tint = if (MaterialTheme.colors.isLight) {
                       LightGray
                   } else {
                       DarkGray
                   }
            )
        }
        Text(
               text = "${likesCount ?: 100}",
               style = MaterialTheme.typography.subtitle2.copy(
                      fontSize = 18.sp
               )
        )

        Spacer(modifier = Modifier.width(MediumSpacing))
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
               text = "${commentsCount ?: 400}",
               style = MaterialTheme.typography.subtitle2.copy(
                      fontSize = 18.sp
               )
        )
    }

}
