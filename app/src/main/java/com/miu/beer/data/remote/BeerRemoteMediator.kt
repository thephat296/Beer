package com.miu.beer.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.miu.beer.data.local.BeerDatabase
import com.miu.beer.data.local.BeerEntity
import com.miu.beer.data.mappers.toBeerEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator @Inject constructor(
    private val beerApi: BeerApi,
    private val beerDb: BeerDatabase
) : RemoteMediator<Int, BeerEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {

        val loadKey = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastId = state.lastItemOrNull()?.id ?: 0
                1 + lastId / state.config.pageSize
            }
        }
        delay(2000)
        return try {
            val beers = beerApi.getBeers(loadKey, 20)
            beerDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    beerDb.beerDao.clearAll()
                }
                beerDb.beerDao.upsertAll(beers.map(BeerDto::toBeerEntity))
            }
            MediatorResult.Success(endOfPaginationReached = beers.isEmpty())
        } catch (e: IOException) {
            Timber.e(e, "Fetching beers error: ")
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            Timber.e(e, "Fetching beers error: ")
            MediatorResult.Error(e)
        }
    }
}