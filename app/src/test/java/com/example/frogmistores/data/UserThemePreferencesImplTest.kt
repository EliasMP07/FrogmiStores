package com.example.frogmistores.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.frogmistores.core.data.repository.UserThemePreferencesImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class UserThemePreferencesImplTest {

    @Mock
    private lateinit var mockDataStore: DataStore<Preferences>

    @Captor
    private lateinit var editCaptor: ArgumentCaptor<suspend (MutablePreferences) -> Unit>

    private lateinit var userThemePreferences: UserThemePreferencesImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userThemePreferences = UserThemePreferencesImpl(mockDataStore)
    }

    @Test
    fun testGetTheme_defaultFalse() = runTest {
        // Given
        `when`(mockDataStore.data).thenReturn(flowOf(emptyPreferences()))

        // When
        val result = userThemePreferences.getTheme().first()

        // Then
        assertEquals(false, result)
    }

    @Test
    fun testSetTheme() = runTest {
        // Given
        val themeKey = booleanPreferencesKey("valueTheme")
        val themeValue = true

        // When
        userThemePreferences.setTheme(themeValue)

        // Then
        verify(mockDataStore).edit(editCaptor.value)
        val mutablePreferences = mutablePreferencesOf()
        runBlocking {
            editCaptor.value.invoke(mutablePreferences)
        }
        assertEquals(themeValue, mutablePreferences[themeKey])
    }

    private fun mutablePreferencesOf(): MutablePreferences {
        return emptyPreferences().toMutablePreferences()
    }
}