package com.github.algamza.itunestrack.repository.local

import androidx.room.RoomDatabase
import com.github.algamza.itunestrack.repository.local.dao.ContentDao
import com.github.algamza.itunestrack.repository.local.entities.ContentEntity
import com.github.algamza.itunestrack.repository.local.entities.FavoriteEntity

@androidx.room.Database(entities = [ContentEntity::class, FavoriteEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun testDao(): ContentDao
}