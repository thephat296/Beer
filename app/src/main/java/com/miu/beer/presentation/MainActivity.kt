package com.miu.beer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.paging.compose.collectAsLazyPagingItems
import com.miu.beer.presentation.beer.BeerScreen
import com.miu.beer.presentation.deprecated.BeerViewModel
import com.miu.beer.presentation.theme.BeerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeerTheme {
                val viewModel: BeerViewModel by viewModels()
                val state = viewModel.beerPagingData.collectAsLazyPagingItems()
                BeerScreen(state)
            }
        }
    }
}