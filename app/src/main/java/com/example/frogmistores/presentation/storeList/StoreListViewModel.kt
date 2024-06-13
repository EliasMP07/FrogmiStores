package com.example.frogmistores.presentation.storeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.frogmistores.domain.GetAllStoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private val getAllStoresUseCase: GetAllStoresUseCase
): ViewModel(){

    val storePaggingFlow = getAllStoresUseCase().cachedIn(viewModelScope)

}