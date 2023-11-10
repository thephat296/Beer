package com.miu.beer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BeerEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?
)