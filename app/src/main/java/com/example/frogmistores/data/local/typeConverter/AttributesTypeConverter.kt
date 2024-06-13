package com.example.frogmistores.data.local.typeConverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.frogmistores.data.local.entities.AttributesEntity
import org.json.JSONObject

@ProvidedTypeConverter
class AttributesTypeConverter() {
    @TypeConverter
    fun fromAttributesEntity(attributes: AttributesEntity): String {
        val jsonObject = JSONObject().apply {
            put("code", attributes.code)
            put("full_address", attributes.fullAddress)
            put("name", attributes.name)
        }
        return jsonObject.toString()
    }

    @TypeConverter
    fun toAttributesEntity(attributesString: String): AttributesEntity {
        val jsonObject = JSONObject(attributesString)
        return AttributesEntity(
            code = jsonObject.getString("code"),
            fullAddress = jsonObject.getString("full_address"),
            name = jsonObject.getString("name")
        )
    }
}