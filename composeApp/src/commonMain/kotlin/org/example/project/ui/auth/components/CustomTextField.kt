package org.example.project.ui.auth.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.example.project.ui.theme.Gray
import org.jetbrains.compose.resources.painterResource
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.hide_eye_icon_filled
import sanboxmultiexp.composeapp.generated.resources.show_eye_icon_filled

@Composable
fun CustomTextField(
       modifier: Modifier = Modifier,
       value: String,
       onValueChange: (String) -> Unit,
       keyboardType: KeyboardType = KeyboardType.Text,
       isPasswordTextField: Boolean = false,
       isSingleLine: Boolean = true,
       hint: String
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
           value = value,
           onValueChange = onValueChange,
           modifier = modifier.fillMaxWidth(),
           textStyle = MaterialTheme.typography.body2,
           keyboardOptions = KeyboardOptions.Default.copy(
                  keyboardType = keyboardType
           ),
           singleLine = isSingleLine,
           colors = TextFieldDefaults.textFieldColors(
                  backgroundColor = if (isSystemInDarkTheme()) {
                      MaterialTheme.colors.surface
                  } else {
                      Gray
                  },
                  unfocusedIndicatorColor = Color.Transparent,
                  focusedIndicatorColor = Color.Transparent
           ),
           trailingIcon = if (isPasswordTextField) {
               {
                   PasswordEyeIcon(isPasswordVisible = isPasswordVisible) {
                       isPasswordVisible = !isPasswordVisible
                   }
               }
           } else {
               null
           },
           visualTransformation = if (isPasswordTextField) {
               if (isPasswordVisible) {
                   VisualTransformation.None
               } else {
                   PasswordVisualTransformation()
               }
           } else {
               VisualTransformation.None
           },
           placeholder = {
               Text(text = hint, style = MaterialTheme.typography.body2)
           },
           shape = MaterialTheme.shapes.medium
    )

}

@Composable
fun PasswordEyeIcon(
       isPasswordVisible: Boolean,
       onPasswordVisibilityToggle: () -> Unit
) {
    val image = if (isPasswordVisible) {
        painterResource( Res.drawable.show_eye_icon_filled)
    } else {
        painterResource(Res.drawable.hide_eye_icon_filled)
    }

    IconButton(onClick = onPasswordVisibilityToggle) {
        Icon(painter = image, contentDescription = null)
    }

}
