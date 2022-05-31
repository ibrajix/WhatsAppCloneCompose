package com.ibrajix.whatsappclonecompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//custom colors
val Colors.greyColor: Color
  get() = if (isLight) GreyLight else GreyDark

val Colors.tealGreenLogo: Color
 get() = if (isLight) TealGreenLogoLight else TealGreenLogoLight

val Colors.subTextColor: Color
get() = if (isLight) SubTextColorLight else SubTextColorDark

val Colors.topBarTextColor: Color
get() = if (isLight) TopBarTextColorLight else TopBarTextColorDark

val Colors.BlueCheck: Color
get() = if (isLight) BlueMain else BlueMain

val Colors.StatusBarColorLight: Color
    get() = TealGreenMainLight

val Colors.StatusBarColorDark: Color
    get() = TealGreenDark

//default
private val DarkColorPalette = darkColors(
    primary = TealGreenDark,
    primaryVariant = TealGreenDark,
    secondary = TealGreenMainLight,
    background = BgDark,
    surface = BgDark
)



private val LightColorPalette = lightColors(
    primary = TealGreenMainLight,
    primaryVariant = TealGreenMainLight,
    secondary = TealGreenMainLight,
    background = BgLight,
    surface = BgLight
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun WhatsAppCloneComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = WhatsAppCloneComposeTypography,
        shapes = Shapes,
        content = content
    )


}