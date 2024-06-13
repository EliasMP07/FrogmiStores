package com.example.frogmistores.presentation.storeList

sealed interface StoreListAction {
    data object OnFavoriteClick: StoreListAction
    data object OnStoreClick: StoreListAction
    data object OnMyFavoritesClick: StoreListAction
}