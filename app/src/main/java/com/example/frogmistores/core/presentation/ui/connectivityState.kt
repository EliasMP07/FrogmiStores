package com.example.frogmistores.core.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import com.example.frogmistores.core.presentation.network.currentConnectivityState
import com.example.frogmistores.core.presentation.network.observeConnectivityAsFlow
import com.example.frogmistores.core.presentation.ui.utils.ConnectionState
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Función composable que observa el estado de conectividad y lo proporciona como un estado composable.
 * Utiliza produceState para crear y manejar el estado basado en un flujo de conectividad.
 *
 * @return El estado de la conectividad.
 */
@ExperimentalCoroutinesApi
@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current
    return produceState(initialValue = context.currentConnectivityState){
        context.observeConnectivityAsFlow().collect { value = it }
    }
}