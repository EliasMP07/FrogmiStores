package com.example.frogmistores.navigation.utils

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Función genérica para crear un tipo de navegación personalizado para objetos Parcelable.
 * Utiliza la biblioteca de serialización de Kotlin para convertir objetos entre su representación JSON y Parcelable.
 *
 * @return Un objeto NavType para el tipo Parcelable especificado.
 */
inline fun <reified T: Parcelable> parcelableTypeOf() = object : NavType<T>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key,value)
    }
}
