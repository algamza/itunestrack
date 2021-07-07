package com.github.algamza.itunestrack.di

import com.github.algamza.itunestrack.repository.Repository
import com.github.algamza.itunestrack.repository.local.dao.ContentDao
import com.github.algamza.itunestrack.repository.remote.RemoteRepo
import com.github.algamza.itunestrack.repository.remote.api.ITunesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRemoteHelper(iTunesService: ITunesService) : RemoteRepo {
        return RemoteRepo(iTunesService)
    }

    @Provides
    @Singleton
    fun provideRepository(contentDao: ContentDao, remoteRepo: RemoteRepo) : Repository {
        return Repository(contentDao, remoteRepo)
    }
}