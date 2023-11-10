package com.miu.beer.presentation.deprecated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.miu.beer.databinding.ItemBeerBinding
import com.miu.beer.domain.model.Beer

class BeerAdapter : PagingDataAdapter<Beer, BeerAdapter.ViewHolder>(Differ()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = getItem(position) ?: return
        holder.bind(beer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer) {
            with(binding) {
                ivBeer.load(beer.imageUrl)
                ivBeer.contentDescription = beer.name
                tvId.text = beer.id.toString()
                tvName.text = beer.name
                tvTagLine.text = beer.tagLine
                tvDescription.text = beer.description
            }
        }
    }

    class Differ : DiffUtil.ItemCallback<Beer>() {
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem == newItem
        }

    }
}
