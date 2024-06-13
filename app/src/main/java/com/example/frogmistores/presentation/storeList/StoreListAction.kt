package com.example.frogmistores.presentation.storeList

import com.example.frogmistores.domain.model.Store

sealed interface StoreListAction {
    data class OnStoreDetailClick(val store: Store): StoreListAction
    data object OnUpdateTheme: StoreListAction
}