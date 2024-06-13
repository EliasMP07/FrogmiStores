package com.example.frogmistores.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class AttributesEntity(
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "full_address")
    val fullAddress: String,
    @ColumnInfo(name = "name")
    val name: String
)