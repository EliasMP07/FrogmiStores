package com.example.frogmistores.core.domain

import kotlinx.coroutines.flow.Flow

interface UserThemePreferences {
    suspend fun getTheme(): Flow<Boolean>
    suspend fun setTheme(value: Boolean)
}
