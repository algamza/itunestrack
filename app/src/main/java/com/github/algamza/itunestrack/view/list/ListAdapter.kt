package com.github.algamza.itunestrack.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.algamza.itunestrack.BR
import com.github.algamza.itunestrack.databinding.RecyclerListBinding
import javax.inject.Inject

class ListAdapter @Inject constructor(): RecyclerView.Adapter<ListAdapter.Holder>() {

    private val differ = AsyncListDiffer(this, DiffCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(RecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<ListData>) {
        differ.submitList(data)
    }

    inner class Holder constructor(private var binding: RecyclerListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListData) { binding.setVariable(BR.data, data) }
    }

    inner class DiffCallback : DiffUtil.ItemCallback<ListData>() {
        override fun areItemsTheSame(oldItem: ListData, newItem: ListData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListData, newItem: ListData): Boolean {
            return oldItem.id == newItem.id && oldItem.favorite == newItem.favorite
        }
    }
}