package org.example.project.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.core.model.fake_data.Comments
import org.example.project.ui.theme.DarkGray
import org.example.project.ui.theme.LargeSpacing
import org.example.project.ui.theme.LightGray
import org.example.project.ui.theme.MediumSpacing
import org.jetbrains.compose.resources.painterResource
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.round_more_horiz_24

@Composable
fun CommentListItem(
       modifier: Modifier = Modifier,
       comment: Comments,
       onProfileClick: (String) -> Unit,
       onMoreIconClick: () -> Unit
) {
    Row(
           modifier = modifier
               .fillMaxWidth()
               .padding(LargeSpacing),
           horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        CircleImage(
               modifier = Modifier.size(30.dp),
               imgUrl = comment.authorImageUrl,
               onClick = {
                   onProfileClick(comment.authorId)
               }
        )
        Column(
               modifier = Modifier.weight(1f),
        ) {
            Row(
                   horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
            ) {
                Text(
                       text = comment.authorName,
                       style = MaterialTheme.typography.subtitle2,
                       modifier = Modifier.alignByBaseline(),
                       color = MaterialTheme.colors.onBackground
                )
                Text(
                       text = comment.data,
                       style = MaterialTheme.typography.caption,
                      // color = if (MaterialTheme.colors.isLight) LightGray else DarkGray,
                       modifier = Modifier.alignByBaseline().weight(1f),
                       color = MaterialTheme.colors.onBackground

                )
                Icon(
                       painter = painterResource(Res.drawable.round_more_horiz_24),
                       contentDescription = null,
                       tint = if (MaterialTheme.colors.isLight) LightGray else DarkGray,
                       modifier = Modifier.clickable { onMoreIconClick() },
                )
            }

            Text(
                   text = comment.comment,
                   style = MaterialTheme.typography.body2
            )
        }
    }

}
