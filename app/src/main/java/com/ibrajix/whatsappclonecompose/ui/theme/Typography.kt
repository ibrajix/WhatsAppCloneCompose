package com.ibrajix.whatsappclonecompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ibrajix.whatsappclonecompose.R


//Custom font
val Helvetica =  FontFamily(
    Font(R.font.helvetica_neue_light, FontWeight.Light),
    Font(R.font.helvetica_neue_normal, FontWeight.Normal),
    Font(R.font.helvetica_neue_medium, FontWeight.Medium),
    Font(R.font.helvetica_neue_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val WhatsAppCloneComposeTypography = Typography(

    body1 = TextStyle(
        fontFamily = Helvetica,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),

    body2 = TextStyle(
        fontFamily = Helvetica,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),

    subtitle1 = TextStyle(
        fontFamily = Helvetica,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    ),

    subtitle2 = TextStyle(
        fontFamily = Helvetica,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    ),


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),

    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */

)
