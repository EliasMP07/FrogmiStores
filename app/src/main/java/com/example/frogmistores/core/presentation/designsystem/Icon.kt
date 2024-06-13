package com.example.frogmistores.core.presentation.designsystem


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.frogmistores.R

val StoreIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.store_icon)

val LocationStore: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.location)

val CodeIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.code_icon)
