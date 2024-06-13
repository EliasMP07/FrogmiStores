package com.example.frogmistores.data.mappers

import com.example.frogmistores.data.local.entities.StoreEntity
import com.example.frogmistores.data.remote.dto.StoreDto
import com.example.frogmistores.domain.model.Store


fun StoreDto.toStoreEntity(): StoreEntity{
    return StoreEntity(
        id = id,
        name = attributes.name,
        fullAddress = attributes.fullAddress,
        code = attributes.code
    )
}


fun StoreEntity.toStore(): Store{
    return Store(
        id = id,
        name = name,
        code = code,
        fullAddress = fullAddress
    )
}