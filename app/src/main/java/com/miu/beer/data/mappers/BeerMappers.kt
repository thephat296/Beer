package com.miu.beer.data.mappers

import com.miu.beer.data.local.BeerEntity
import com.miu.beer.data.remote.BeerDto
import com.miu.beer.domain.model.Beer

fun BeerDto.toBeerEntity() = BeerEntity(
    id = id,
    name = name,
    tagline = tagline,
    description = description,
    imageUrl = imageUrl
)

fun BeerEntity.toBeer() = Beer(
    id = id,
    name = name,
    tagLine = tagline,
    description = description,
    imageUrl = imageUrl
)