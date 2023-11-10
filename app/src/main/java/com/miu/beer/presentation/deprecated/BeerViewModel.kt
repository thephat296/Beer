package com.miu.beer.presentation.deprecated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.miu.beer.data.local.BeerEntity
import com.miu.beer.data.mappers.toBeer
import com.miu.beer.domain.model.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    pager: Pager<Int, BeerEntity>
) : ViewModel() {

    val beerPagingData: Flow<PagingData<Beer>> = pager.flow
        .map {
            it.map(BeerEntity::toBeer)
        }
        .cachedIn(viewModelScope)
}