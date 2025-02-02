package org.example.project.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.example.project.core.model.fake_data.FollowsUser
import org.example.project.ui.theme.LargeSpacing
import org.example.project.ui.theme.MediumSpacing
import org.jetbrains.compose.resources.stringResource
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.oboarding_guidance_subtitle
import sanboxmultiexp.composeapp.generated.resources.onboarding_button_text
import sanboxmultiexp.composeapp.generated.resources.onboarding_title

@Composable
fun OnBoardingSection(
       modifier: Modifier = Modifier,
       users: List<FollowsUser>,
       onUserClick: () -> Unit,
       onFollowButtonClick: (Boolean, FollowsUser) -> Unit,
       onBoardingFinish: () -> Unit
) {
    Column(
           modifier = modifier.fillMaxWidth()
    ) {
        Text(
               text = stringResource(Res.string.onboarding_title),
               modifier = modifier
                   .fillMaxWidth()
                   .padding(top = MediumSpacing),
               style = MaterialTheme.typography.subtitle1,
               textAlign = TextAlign.Center
        )

        Text(
               text = stringResource(Res.string.oboarding_guidance_subtitle),
               modifier = modifier
                   .fillMaxWidth()
                   .padding(horizontal = LargeSpacing),
               style = MaterialTheme.typography.body2,
               textAlign = TextAlign.Center
        )

        Spacer(modifier = modifier.height(LargeSpacing))

        UsersRow(
               users = users,
               onUserClick = onUserClick,
               onFollowButtonClick = onFollowButtonClick
        )

        OutlinedButton(
               onClick = onBoardingFinish,
               shape = RoundedCornerShape(percent = 50),
               modifier = modifier
                   .fillMaxWidth(fraction = 0.5f)
                   .align(Alignment.CenterHorizontally)
                   .padding(vertical = LargeSpacing)
        ) {
            Text(text = stringResource(Res.string.onboarding_button_text))
        }
    }
}

@Composable
private fun UsersRow(
       modifier: Modifier = Modifier,
       users: List<FollowsUser>,
       onUserClick: () -> Unit,
       onFollowButtonClick: (Boolean, FollowsUser) -> Unit
) {
    LazyRow(
           horizontalArrangement = Arrangement.spacedBy(LargeSpacing),
           contentPadding = PaddingValues(horizontal = LargeSpacing),
           modifier = modifier
    ) {
        items(items = users, key = { user -> user.id }) {
            OnboardingUserItem(
                   followsUser = it,
                   onUserClick = onUserClick,
                   onFollowBtnClick = onFollowButtonClick
            )
        }
    }
}
