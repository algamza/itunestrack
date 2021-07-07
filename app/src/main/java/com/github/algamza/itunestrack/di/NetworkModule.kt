package com.github.algamza.itunestrack.di

import com.github.algamza.itunestrack.BuildConfig
import com.github.algamza.itunestrack.repository.remote.api.ITunesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    val BASE_URL = "https://itunes.apple.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideITunesRetrofit(okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideITunesService(retrofit: Retrofit): ITunesService =
        retrofit.create(ITunesService::class.java)
}