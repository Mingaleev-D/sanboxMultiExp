package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.core.model.fake_data.sampleComments
import org.example.project.ui.components.CommentListItem
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
            CommentListItem(
                   comment = sampleComments[0],
                   onProfileClick = {},
                   onMoreIconClick = {}
            )
        }
    }
}
