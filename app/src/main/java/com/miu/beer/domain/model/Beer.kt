package com.miu.beer.domain.model

data class Beer(
    val id: Int,
    val name: String,
    val tagLine: String,
    val description: String,
    val imageUrl: String?
)