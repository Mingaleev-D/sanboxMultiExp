package org.example.project.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.example.project.core.model.fake_data.FollowsUser
import org.example.project.ui.components.CircleImage
import org.example.project.ui.components.FollowsBtn
import org.example.project.ui.theme.MediumSpacing
import org.example.project.ui.theme.SmallSpacing
import org.jetbrains.compose.resources.stringResource
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.follow_text_label
import sanboxmultiexp.composeapp.generated.resources.followers_text

@Composable
fun OnboardingUserItem(
       modifier: Modifier = Modifier,
       followsUser: FollowsUser,
       onUserClick: () -> Unit,
       isFollowing: Boolean = false,
       onFollowBtnClick: (Boolean, FollowsUser) -> Unit
) {
    Card(
           modifier = modifier
               .height(140.dp)
               .width(130.dp)
               .clickable { onUserClick() },
           elevation = 4.dp
    ) {
        Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(MediumSpacing),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleImage(
                   modifier = Modifier.size(50.dp),
                   imgUrl = followsUser.profileUrl,
                   onClick = {
                       onUserClick()
                   }
            )

            Spacer(modifier = Modifier.height(SmallSpacing))

            Text(
                   text = followsUser.name,
                   style = MaterialTheme.typography.subtitle2,
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(MediumSpacing))
            FollowsBtn(
                   text = stringResource(Res.string.follow_text_label),
                   onClick = {
                       onFollowBtnClick(!isFollowing, followsUser)
                   },
                   isOutline = false
            )
        }
    }

}
