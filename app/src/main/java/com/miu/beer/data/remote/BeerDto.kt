package com.miu.beer.data.remote

import com.google.gson.annotations.SerializedName

data class BeerDto(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String?
)