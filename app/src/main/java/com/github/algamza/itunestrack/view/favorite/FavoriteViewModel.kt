package com.github.algamza.itunestrack.view.favorite

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.algamza.itunestrack.repository.Repository


class FavoriteViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var favorite: LiveData<List<FavoriteData>> = Transformations.map(repository.favorite) {
        var arr = ArrayList<FavoriteData>()
        for ( data in it ) {
            if ( !data.favorite ) continue
            arr.add(FavoriteData(callback, data.id, data.name, data.group, data.artist, data.url, data.favorite))
        }
        arr
    }

    private var callback = (object: FavoriteData.Callback {
        override fun onClickFavorite(id: Int, favorite: Boolean) {
            repository.updateFavorite(id, favorite)
        }
    })
}