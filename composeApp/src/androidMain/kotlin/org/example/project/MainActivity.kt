package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.core.model.fake_data.sampleUsers
import org.example.project.ui.home.components.OnboardingUserItem
import org.example.project.ui.theme.SocialAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            App()
        }
    }
}
//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}
@Preview(showBackground = true)
@Composable
private fun DemoPreview() {
    SocialAppTheme {
        Box(
               modifier = Modifier.fillMaxSize(),
        ) {
            OnboardingUserItem(
                   followsUser = sampleUsers[0],
                   onUserClick = {
                   },
                   isFollowing = true,
                   onFollowBtnClick = { _, _ ->
                   }
            )
        }
    }
}
