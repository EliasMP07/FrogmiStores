package com.example.frogmistores.presentation.storeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.frogmistores.data.local.entities.StoreEntity
import com.example.frogmistores.data.mappers.toStore
import com.example.frogmistores.domain.GetAllStoresUseCase
import com.example.frogmistores.domain.repository.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val getAllStoresUseCase: GetAllStoresUseCase
): ViewModel(){

    val storePaggingFlow = getAllStoresUseCase().cachedIn(viewModelScope)

    private var _state = MutableStateFlow(StoreListState())
    val state: StateFlow<StoreListState> get() = _state.asStateFlow()

    fun onAction(
        action: StoreListAction
    ){
        when(action){
            else -> Unit
        }
    }

}