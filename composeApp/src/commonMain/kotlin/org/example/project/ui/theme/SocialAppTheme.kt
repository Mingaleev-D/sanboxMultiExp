package org.example.project.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import sanboxmultiexp.composeapp.generated.resources.Res
import sanboxmultiexp.composeapp.generated.resources.lexend_bold
import sanboxmultiexp.composeapp.generated.resources.lexend_medium
import sanboxmultiexp.composeapp.generated.resources.lexend_semi_bold
import sanboxmultiexp.composeapp.generated.resources.open_sans_light
import sanboxmultiexp.composeapp.generated.resources.open_sans_regular

@Composable
fun SocialAppTheme(
       darkTheme: Boolean = isSystemInDarkTheme(),
       content: @Composable () -> Unit
) {

    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
           colors = colors,
           shapes = Shapes,
          // typography = Typography,
           content = content
    )
}


val SmallSpacing = 4.dp
val MediumSpacing = 8.dp
val LargeSpacing = 16.dp

val ExtraLargeSpacing = 24.dp
val SmallElevation = 2.dp
val MediumElevation = 4.dp


val ButtonHeight = 52.dp

val Blue = Color(0xFF1E80F8)
val Gray = Color(0xFFF3F3F4)


val Black = Color(0xFF000000)

val Black87 = Color(0xFF18191A)
val DarkGray = Color(0xFF999A9A)

val Black54 = Color(0xFF373B3F)
val Black24 = Color(0xFF242526)


val White = Color(0xFFFFFFFF)

val White87 = Color(0xFFE2E2E2)
val LightGray = Color(0xFF8A8A8D)

val White36 = Color(0xFFE5E5E5)
val White76 = Color(0xFFF5F5F5)

internal val LightColors = lightColors(
       primary = Blue,
       primaryVariant = Blue,
       background = White76,
       onBackground = Black87,
       surface = White,
       onSurface = Black87
)

internal val DarkColors = darkColors(
       primary = Blue,
       primaryVariant = Blue,
       background = Black87,
       onBackground = White87,
       surface = Black24,
       onSurface = White87
)

val Shapes = Shapes(
       small = RoundedCornerShape(SmallSpacing),
       medium = RoundedCornerShape(MediumSpacing),
       large = RoundedCornerShape(LargeSpacing)
)

//val Lexend = FontFamily(
//       Font(Res.font.lexend_medium, FontWeight.Medium),
//       Font(Res.font.lexend_semi_bold, FontWeight.SemiBold),
//       Font(Res.font.lexend_bold, FontWeight.Bold)
//)
//val OpenSans = FontFamily(
//       Font(Res.font.open_sans_light, FontWeight.Light),
//       Font(Res.font.open_sans_regular, FontWeight.Normal)
//)
//
//val Typography = Typography(
//       h6 = TextStyle(
//              fontFamily = Lexend,
//              fontWeight = FontWeight.Bold,
//              fontSize = 21.sp
//       ),
//       subtitle1 = TextStyle(
//              fontFamily = Lexend,
//              fontWeight = FontWeight.SemiBold,
//              fontSize = 18.sp
//       ),
//       subtitle2 = TextStyle(
//              fontFamily = Lexend,
//              fontWeight = FontWeight.Medium,
//              fontSize = 14.sp
//       ),
//       body1 = TextStyle(
//              fontFamily = OpenSans,
//              fontWeight = FontWeight.Normal,
//              fontSize = 16.sp
//       ),
//       body2 = TextStyle(
//              fontFamily = OpenSans,
//              fontWeight = FontWeight.Normal,
//              fontSize = 14.sp
//       ),
//       button = TextStyle(
//              fontFamily = Lexend,
//              fontWeight = FontWeight.SemiBold,
//              fontSize = 15.sp
//       ),
//       caption = TextStyle(
//              fontFamily = OpenSans
//       ),
//       overline = TextStyle(
//              fontFamily = OpenSans
//       )
//)
