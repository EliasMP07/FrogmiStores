package com.example.frogmistores.data.mappers

import com.example.frogmistores.data.local.entities.StoreEntity
import com.example.frogmistores.data.remote.dto.StoreDto
import com.example.frogmistores.domain.model.Store

/**
 * Extensión para convertir un objeto StoreDto a StoreEntity.
 * @return Un objeto StoreEntity correspondiente a este StoreDto.
 */
fun StoreDto.toStoreEntity(): StoreEntity{
    return StoreEntity(
        id = id,
        name = attributesDto.name,
        fullAddress = attributesDto.fullAddress,
        code = attributesDto.code,
        latitude = attributesDto.coordinates.latitude,
        longitude = attributesDto.coordinates.longitude

    )
}

/**
 * Extensión para convertir un objeto StoreEntity a Store.
 * @return Un objeto Store correspondiente a este StoreEntity.
 */
fun StoreEntity.toStore(): Store{
    return Store(
        id = id,
        name = name,
        code = code,
        fullAddress = fullAddress,
        latitude = latitude,
        longitude = longitude
    )
}