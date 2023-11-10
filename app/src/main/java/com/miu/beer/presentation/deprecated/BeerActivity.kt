package com.miu.beer.presentation.deprecated

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.beer.databinding.ActivityBeerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeerActivity : AppCompatActivity() {

    private val viewModel: BeerViewModel by viewModels()
    private lateinit var binding: ActivityBeerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val beerAdapter = BeerAdapter()
        with(binding.rvBeer) {
            adapter = beerAdapter.withLoadStateFooter(BeerLoadStateAdapter(beerAdapter::retry))
            layoutManager = LinearLayoutManager(this@BeerActivity)
            addItemDecoration(
                DividerItemDecoration(this@BeerActivity, LinearLayoutManager.VERTICAL)
            )
        }

        lifecycleScope.launch {
            viewModel.beerPagingData
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect(beerAdapter::submitData)
        }
    }
}