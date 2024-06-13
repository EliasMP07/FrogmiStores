package com.example.frogmistores.presentation.storeList

sealed interface StoreListAction {
    data object OnStoreClick: StoreListAction
    data object OnUpdateTheme: StoreListAction
}