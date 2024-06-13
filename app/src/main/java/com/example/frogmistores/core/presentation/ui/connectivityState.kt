package com.example.frogmistores.core.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import com.example.frogmistores.core.presentation.network.currentConnectivityState
import com.example.frogmistores.core.presentation.network.observeConnectivityAsFlow
import com.example.frogmistores.core.presentation.ui.utils.ConnectionState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current
    return produceState(initialValue = context.currentConnectivityState){
        context.observeConnectivityAsFlow().collect { value = it }
    }
}