package com.example.frogmistores.presentation.storeDetail

sealed interface StoreDetailAction{
    data object OnBackClick: StoreDetailAction
}