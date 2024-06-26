package com.example.frogmistores.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.frogmistores.core.domain.UserThemePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementación de la interfaz UserThemePreferences que utiliza DataStore para gestionar las preferencias del tema del usuario.
 *
 * @param userPreferences DataStore<Preferences> instancia de DataStore que se utiliza para leer y escribir las preferencias.
 */
class UserThemePreferencesImpl(
    private val userPreferences: DataStore<Preferences>
): UserThemePreferences {

    companion object{
        private val THEME = booleanPreferencesKey("valueTheme")
    }

    override suspend fun getTheme(): Flow<Boolean> = userPreferences.data.map {preferences ->
        preferences[THEME]?: false
    }

    override suspend fun setTheme(value: Boolean) {
        userPreferences.edit { preferents ->
            preferents[THEME] = value
        }
    }
}