package com.github.algamza.itunestrack.view.list

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.algamza.itunestrack.repository.Repository

class ListViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var content: LiveData<List<ListData>> = Transformations.map(repository.content) {
        it.map { ListData(callback, it.id, it.name, it.group, it.artist, it.url, it.favorite) }
    }

    fun requestTrack(limit: Int, offset: Int) {
        repository.requestTrack(limit, offset)
    }

    private var callback = (object: ListData.Callback {
        override fun onClickFavorite(id: Int, favorite: Boolean) {
            repository.updateFavorite(id, favorite)
        }
    })
}