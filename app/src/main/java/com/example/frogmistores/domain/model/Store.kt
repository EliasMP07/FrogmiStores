

package com.example.frogmistores.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Store(
    val id: String = "",
    val name: String = "",
    val code: String = "",
    val fullAddress: String = "",
    val longitude: Double = 0.0,
    val latitude: Double =  0.0,
): Parcelable
