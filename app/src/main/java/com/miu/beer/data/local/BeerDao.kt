package com.miu.beer.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BeerDao {
    @Upsert
    fun upsertAll(beers: List<BeerEntity>)

    @Query("SELECT * FROM BeerEntity")
    fun pagingSource(): PagingSource<Int, BeerEntity>

    @Query("DELETE FROM BeerEntity")
    fun clearAll()
}
