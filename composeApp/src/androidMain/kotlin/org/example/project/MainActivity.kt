package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.ui.theme.SocialAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

@Preview(showBackground = true)
@Composable
private fun DemoPreview() {
    SocialAppTheme {
        Box(
               modifier = Modifier.fillMaxSize(),
        ) {
            Icon(
                   imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                   contentDescription = null,
                   modifier = Modifier
                       .align(Alignment.CenterStart) // Размещаем слева
                       .padding(start = 16.dp)
            )

            Text(
                   text = "SignUp",
                   modifier = Modifier.align(Alignment.Center), // Центрируем текст
                   textAlign = TextAlign.Center,
                  // style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
