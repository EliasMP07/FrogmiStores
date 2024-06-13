package com.example.frogmistores.core.presentation.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.example.frogmistores.core.presentation.ui.utils.ConnectionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

/**
 * Extensión para obtener el estado actual de la conectividad de la red.
 */
val Context.currentConnectivityState: ConnectionState
    get() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        return getCurrentConnectivityState(connectivityManager)
    }

/**
 * Función para observar el estado de la conectividad como un flujo (Flow).
 * Utiliza callbackFlow para emitir los cambios en el estado de la conectividad.
 */
@ExperimentalCoroutinesApi
fun Context.observeConnectivityAsFlow() = callbackFlow {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = NetworkCallback{ state-> trySend(state) }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    connectivityManager.registerNetworkCallback(networkRequest, callback)

    val currentState = getCurrentConnectivityState(connectivityManager)
    trySend(currentState)

    awaitClose {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}

/**
 * Función auxiliar para obtener el estado actual de la conectividad.
 * Verifica todas las redes y determina si hay conectividad a Internet.
 */
private fun getCurrentConnectivityState(
    connectivityManager: ConnectivityManager
): ConnectionState {
    val connected = connectivityManager.allNetworks.any { network->
        connectivityManager.getNetworkCapabilities(network)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }
    return if (connected) ConnectionState.Available else ConnectionState.Unavailable
}

/**
 * Función para crear un NetworkCallback que recibe un lambda para manejar los cambios en el estado de la conectividad.
 * @param callback Lambda que se ejecuta cuando cambia el estado de la conectividad.
 * @return Un objeto ConnectivityManager.NetworkCallback.
 */
fun NetworkCallback(callback: (ConnectionState)-> Unit)
        : ConnectivityManager.NetworkCallback{
    return object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            callback(ConnectionState.Available)
        }

        override fun onLost(network: Network) {
            callback(ConnectionState.Unavailable)
        }
    }
}