package com.miu.beer.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.miu.beer.data.local.BeerDatabase
import com.miu.beer.data.local.BeerEntity
import com.miu.beer.data.remote.BeerApi
import com.miu.beer.data.remote.BeerRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext appContext: Context): BeerDatabase {
        return Room.databaseBuilder(
            context = appContext,
            klass = BeerDatabase::class.java,
            name = BeerDatabase.NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi(@ApplicationContext appContext: Context): BeerApi {
        return Retrofit.Builder()
            .baseUrl(BeerApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(
        beerDatabase: BeerDatabase,
        beerApi: BeerApi
    ): Pager<Int, BeerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            initialKey = 0,
            remoteMediator = BeerRemoteMediator(beerApi, beerDatabase),
            pagingSourceFactory = {
                beerDatabase.beerDao.pagingSource()
            }
        )
    }
}