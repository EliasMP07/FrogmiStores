package com.example.frogmistores.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.frogmistores.core.data.repository.UserThemePreferencesImpl
import com.example.frogmistores.core.data.repository.utils.PreferencesUser.USER_PREFERENCES
import com.example.frogmistores.core.domain.UserThemePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * Módulo de Dagger para proporcionar dependencias relacionadas con DataStore y UserThemePreferences.
 */
@Module
@InstallIn(SingletonComponent::class)
object CoreModule {


    /**
     * Proporciona una instancia de DataStore<Preferences> configurada con manejo de corrupción,
     * migraciones y un scope de corrutinas.
     *
     * @param context Context el contexto de la aplicación.
     * @return DataStore<Preferences> la instancia de DataStore para almacenar preferencias.
     */
    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(context, USER_PREFERENCES)),
            scope = CoroutineScope(
                context = Dispatchers.IO + SupervisorJob(),

                ),
            produceFile = { context.preferencesDataStoreFile(USER_PREFERENCES)})
    }
    @Singleton
    @Provides
    fun provideUserThemePreferences (userPreferences: DataStore<Preferences>): UserThemePreferences{
        return UserThemePreferencesImpl(userPreferences)
    }
}
