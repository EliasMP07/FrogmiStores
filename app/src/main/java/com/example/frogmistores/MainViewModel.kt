package com.example.frogmistores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frogmistores.core.domain.UserThemePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para la pantalla principal.
 * Utiliza Hilt para la inyección de dependencias.
 *
 * @property userThemePreferences Preferencias del tema del usuario.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userThemePreferences: UserThemePreferences
): ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state : StateFlow<MainState> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            userThemePreferences.getTheme().collect{ valueTheme ->
                _state.update {
                    it.copy(
                        themeValue = valueTheme
                    )
                }
            }
        }
    }

    fun onAction(
        action: MainAction
    ){
        when(action){
            MainAction.OnUpdateTheme -> {
                viewModelScope.launch {
                    userThemePreferences.setTheme(!state.value.themeValue!!)
                }
            }
        }
    }
}