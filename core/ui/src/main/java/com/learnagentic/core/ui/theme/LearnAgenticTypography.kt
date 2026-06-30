package com.learnagentic.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// TODO (Frontend Agent): Replace with actual Interphases font files placed in core/ui/src/main/res/font/
// val InterphasesFont = FontFamily(
//     Font(R.font.interphases_regular, FontWeight.Normal),
//     Font(R.font.interphases_medium, FontWeight.Medium),
//     Font(R.font.interphases_bold, FontWeight.Bold),
// )

val LearnAgenticTypography = Typography(
    displayLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 57.sp, lineHeight = 64.sp),
    headlineLarge = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 32.sp, lineHeight = 40.sp),
    headlineMedium = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 28.sp, lineHeight = 36.sp),
    titleLarge = TextStyle(fontWeight = FontWeight.Medium, fontSize = 22.sp, lineHeight = 28.sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    labelLarge = TextStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp),
)
