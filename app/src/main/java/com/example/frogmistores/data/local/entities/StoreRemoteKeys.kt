package com.example.frogmistores.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StoreRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
