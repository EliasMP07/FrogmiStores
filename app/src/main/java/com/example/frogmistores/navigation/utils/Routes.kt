package com.example.frogmistores.navigation.utils

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.example.frogmistores.domain.model.Store
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

/**
 * Clase sellada que representa las diferentes pantallas de navegación en la aplicación.
 * @property route La ruta de navegación asociada a la pantalla.
 */
sealed class Screen(val route: String) {
    data object StoreList : Screen("storeList")
    data object StoreDetail: Screen("detail/{${NavArgs.StoreID.key}}") {
        fun createRoute(store: Store) = "detail/${Uri.encode(Json.encodeToJsonElement(store).toString())}"
    }
}


/**
 * Enumeración que representa los argumentos de navegación.
 * @property key La clave del argumento de navegación.
 */
enum class NavArgs(val key: String) {
    StoreID("store")
}

