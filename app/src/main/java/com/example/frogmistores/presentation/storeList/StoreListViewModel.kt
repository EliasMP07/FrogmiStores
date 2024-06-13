package com.example.frogmistores.presentation.storeList

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(

): ViewModel(){

    private var _state = MutableStateFlow(StoreListState())
    val state: StateFlow<StoreListState> get() = _state.asStateFlow()

    fun onAction(
        action: StoreListAction
    ){
        when(action){
            StoreListAction.OnFavoriteClick -> TODO()
            else -> Unit
        }
    }

}