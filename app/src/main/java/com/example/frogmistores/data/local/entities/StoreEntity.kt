package com.example.frogmistores.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.frogmistores.data.local.typeConverter.AttributesTypeConverter

@Entity
data class StoreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "attributes")
    @TypeConverters(AttributesTypeConverter::class)
    val attributes: AttributesEntity
)