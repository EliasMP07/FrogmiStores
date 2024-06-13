package com.example.frogmistores.core.presentation.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.frogmistores.R

val ValeraRound = FontFamily(
    Font(
        resId = R.font.valera_round,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.valera_round,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.valera_round,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.valera_round,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.valera_round,
        weight = FontWeight.Bold
    ),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = ValeraRound,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Justify
    ),
    bodyMedium = TextStyle(
        fontFamily = ValeraRound,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = ValeraRound,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = ValeraRound,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = ValeraRound,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
)