package com.miu.beer.presentation.deprecated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.miu.beer.domain.model.Beer
import com.miu.beer.domain.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    beerRepository: BeerRepository
) : ViewModel() {

    val beerPagingData: Flow<PagingData<Beer>> = beerRepository.getBeers().cachedIn(viewModelScope)
}