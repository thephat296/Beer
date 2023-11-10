package com.miu.beer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BeerEntity::class], version = 1)
abstract class BeerDatabase : RoomDatabase() {
    abstract val beerDao: BeerDao

    companion object {
        const val NAME = "beers.db"
    }
}