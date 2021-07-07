package com.github.algamza.itunestrack.repository.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.algamza.itunestrack.repository.local.entities.ContentEntity
import com.github.algamza.itunestrack.repository.local.entities.ContentFavoriteEntity
import com.github.algamza.itunestrack.repository.local.entities.FavoriteContentEntity
import com.github.algamza.itunestrack.repository.local.entities.FavoriteEntity

@Dao
interface ContentDao {
    @Query("SELECT * FROM ContentEntity")
    fun getContentEntities() : LiveData<List<ContentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContentEntities(entities: List<ContentEntity>)

    @Query("DELETE FROM ContentEntity")
    fun deleteContentEntities()

    @Query("SELECT * FROM FavoriteEntity")
    fun getFavoriteEntities() : LiveData<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteEntity(entities: FavoriteEntity)

    @Query("DELETE FROM FavoriteEntity WHERE id =:id")
    fun deleteFavoriteEntity(id: Int)

    @Query("DELETE FROM FavoriteEntity")
    fun deleteFavoriteEntities()

    @Query("SELECT * FROM ContentEntity")
    fun getContentFavoriteEntities() : LiveData<List<ContentFavoriteEntity>>

    @Query("SELECT * FROM FavoriteEntity")
    fun getFavoriteContentEntities() : LiveData<List<FavoriteContentEntity>>
}