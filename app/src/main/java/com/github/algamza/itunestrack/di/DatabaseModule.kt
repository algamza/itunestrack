package com.github.algamza.itunestrack.di

import android.content.Context
import androidx.room.Room
import com.github.algamza.itunestrack.repository.local.LocalDatabase
import com.github.algamza.itunestrack.repository.local.dao.ContentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : LocalDatabase {
        return Room.databaseBuilder(context, LocalDatabase::class.java, "itunestrack.db").build()
    }

    @Provides
    fun provideTestDao(database: LocalDatabase) : ContentDao { return database.testDao() }
}