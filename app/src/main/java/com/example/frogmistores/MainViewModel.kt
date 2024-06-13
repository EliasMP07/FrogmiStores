package com.example.frogmistores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frogmistores.core.domain.UserThemePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userThemePreferences: UserThemePreferences
): ViewModel() {

    private val _themeValue = MutableStateFlow(false)
    val themeValue: StateFlow<Boolean> get() = _themeValue
    init {
        viewModelScope.launch {
            userThemePreferences.getTheme().collect{
                _themeValue.value = it
            }
        }
    }

    fun onAction(
        action: MainAction
    ){
        when(action){
            MainAction.OnUpdateTheme -> {
                viewModelScope.launch {
                    userThemePreferences.setTheme(!_themeValue.value)
                }
            }
        }
    }
}