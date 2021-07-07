package com.github.algamza.itunestrack.view.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.algamza.itunestrack.BR
import com.github.algamza.itunestrack.databinding.RecyclerFavoriteBinding
import javax.inject.Inject

class FavoriteAdapter @Inject constructor(): RecyclerView.Adapter<FavoriteAdapter.Holder>() {

    private val differ = AsyncListDiffer(this, DiffCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(RecyclerFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<FavoriteData>) {
        differ.submitList(data)
    }

    inner class Holder constructor(private var binding: RecyclerFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FavoriteData) { binding.setVariable(BR.data, data) }
    }

    inner class DiffCallback : DiffUtil.ItemCallback<FavoriteData>() {
        override fun areItemsTheSame(oldItem: FavoriteData, newItem: FavoriteData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteData, newItem: FavoriteData): Boolean {
            return oldItem.id == newItem.id && oldItem.favorite == newItem.favorite
        }
    }
}