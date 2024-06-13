

package com.example.frogmistores.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Store(
    val id: String,
    val name: String,
    val code: String,
    val fullAddress: String
): Parcelable
