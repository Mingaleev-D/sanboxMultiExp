package org.example.project.ui.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.core.model.fake_data.AllPostDTO
import org.jetbrains.compose.resources.painterResource
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.person_circle_icon
import sanboxmultiexp.composeapp.generated.resources.round_arrow_back

@Composable
fun PostDetailRoot(
       navController: NavController,
       //  post: AllPostDTO
) {
     PostDetails()

}

@Composable
fun PostDetails(
       modifier: Modifier = Modifier
) {
    Column(
           modifier = Modifier.fillMaxSize(),
    ) {
        Row(
               modifier = Modifier.padding(start = 6.dp, end = 10.dp),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                   onClick = {
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
                   text = "Details",
                   style = MaterialTheme.typography.body1.copy(
                          fontFamily = FontFamily.Monospace,
                          fontSize = 16.sp,
                          fontWeight = FontWeight.Bold
                   ),
                   color = MaterialTheme.colors.onSurface
            )

        }
        Text(
               text = "PostDetails",
        )
    }

}
