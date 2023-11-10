package com.miu.beer.domain.repository

import androidx.paging.PagingData
import com.miu.beer.domain.model.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    fun getBeers(): Flow<PagingData<Beer>>
}