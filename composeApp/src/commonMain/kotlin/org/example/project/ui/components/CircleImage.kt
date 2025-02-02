package org.example.project.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.dark_image_place_holder
import sanboxmultiexp.composeapp.generated.resources.light_image_place_holder

@Composable
fun CircleImage(
       modifier: Modifier = Modifier,
       imgUrl: String?,
       onClick: () -> Unit
) {
    AsyncImage(
           model = imgUrl ?: "https://services.google.com/fh/files/emails/image6_play_newsletter_march_2021.png",
           contentDescription = null,
           modifier = modifier
               .clip(CircleShape)
               .clickable { onClick() },
           placeholder = if (MaterialTheme.colors.isLight) {
               painterResource(Res.drawable.light_image_place_holder)
           } else {
               painterResource(Res.drawable.dark_image_place_holder)
           },
           contentScale = ContentScale.Crop,
    )
}
