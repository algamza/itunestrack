package com.github.algamza.itunestrack.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.algamza.itunestrack.model.ContentModel
import com.github.algamza.itunestrack.repository.local.dao.ContentDao
import com.github.algamza.itunestrack.repository.local.entities.ContentEntity
import com.github.algamza.itunestrack.repository.local.entities.FavoriteEntity
import com.github.algamza.itunestrack.repository.remote.RemoteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(
        private var contentDao: ContentDao,
        private var remoteRepo: RemoteRepo) {
    var content: LiveData<List<ContentModel>> = Transformations.map(
        contentDao.getContentFavoriteEntities()) {
        val models = ArrayList<ContentModel>()
        for ( entity in it ) {
            var _content = entity.content
            var _favorite = entity.favorite
            models.add(ContentModel(_content.id, _content.name, _content.group,
                _content.artist, _content.url, when(_favorite) {
                null -> false
                else -> _favorite.favorite
            }))
        }
        models
    }
        get() {
            requestTrack(30, 0)
            return field
        }

    var favorite: LiveData<List<ContentModel>> = Transformations.map(contentDao.getFavoriteContentEntities()) {
        val models = ArrayList<ContentModel>()
        for ( entity in it ) {
            var _content = entity.content
            var _favorite = entity.favorite
            if ( _content == null ) continue
            models.add(ContentModel(_content.id, _content.name, _content.group,
                _content.artist, _content.url, _favorite.favorite))
        }
        models
    }

    fun updateFavorite(id: Int, favorite: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            if ( favorite ) contentDao.insertFavoriteEntity(FavoriteEntity(id, favorite))
            else contentDao.deleteFavoriteEntity(id)
        }
    }

    fun requestTrack(limit: Int, offset: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            contentDao.insertContentEntities(remoteRepo.requestTrack(
                "greenday", "song", limit, offset).results.map {
                ContentEntity(it.trackId, it.trackName, it.collectionName, it.artistName, it.artworkUrl60)
            })
        }
    }

    fun requestTrackWithClear(limit: Int, offset: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            contentDao.deleteContentEntities()
            requestTrack(limit, offset)
        }
    }
}