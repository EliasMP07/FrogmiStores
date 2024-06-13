package com.example.frogmistores

sealed interface MainAction {
    data object OnUpdateTheme: MainAction
}