package com.ibrajix.whatsappclonecompose.ui.theme

import android.annotation.SuppressLint
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

val Colors.blueCheck: Color
get() = if (isLight) BlueMain else BlueMain

val Colors.statusBarColorLight: Color
    get() = TealGreenMainLight

val Colors.statusBarColorDark: Color
    get() = TealGreenDark

val Colors.tealGreenOpacity: Color
    get() = TealGreenDarkOpacity

val Colors.tabInActiveColor: Color
    get() = if (isLight) TopBarTextColorLight else SubTextColorDark

val Colors.tabActiveColor: Color
    get() = if  (isLight)  TopBarTextColorLight else TealGreenMainLight

//default

private val DarkColorPalette = darkColors(
    primary = TealGreenDark,
    primaryVariant = TealGreenDark,
    secondary = TealGreenMainLight,
    background = BgDark,
    surface = BgDark,
    onBackground = BgLight,
    onSurface = BgLight
)



@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = TealGreenMainLight,
    primaryVariant = TealGreenMainLight,
    secondary = TealGreenMainLight,
    background = BgLight,
    surface = BgLight,
    onBackground = BgDark,
    onSurface = BgDark
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