package com.miu.beer.di

import com.miu.beer.data.remote.BeerRepositoryImpl
import com.miu.beer.domain.repository.BeerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface BeerModule {

    @Binds
    fun bindBeerRepository(beerRepositoryImpl: BeerRepositoryImpl): BeerRepository
}