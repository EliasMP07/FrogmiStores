package com.example.frogmistores.presentation.storeList

import com.example.frogmistores.MainAction
import com.example.frogmistores.domain.model.Store

sealed interface StoreListAction {
    data object OnStoreClick: StoreListAction
    data object OnUpdateTheme: StoreListAction
}