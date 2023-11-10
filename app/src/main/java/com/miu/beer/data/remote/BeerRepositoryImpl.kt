package com.miu.beer.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.miu.beer.data.local.BeerDatabase
import com.miu.beer.data.local.BeerEntity
import com.miu.beer.data.mappers.toBeer
import com.miu.beer.domain.model.Beer
import com.miu.beer.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val beerRemoteMediator: BeerRemoteMediator,
    private val beerDatabase: BeerDatabase
) : BeerRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getBeers(): Flow<PagingData<Beer>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            initialKey = 0,
            remoteMediator = beerRemoteMediator,
            pagingSourceFactory = beerDatabase.beerDao::pagingSource
        )
            .flow
            .map {
                it.map(BeerEntity::toBeer)
            }
    }
}