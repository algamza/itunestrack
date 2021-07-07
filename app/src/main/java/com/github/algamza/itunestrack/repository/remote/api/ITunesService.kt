package com.github.algamza.itunestrack.repository.remote.api

import com.github.algamza.itunestrack.repository.remote.response.ITunesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesService {
    @GET("/search?")
    suspend fun requestTrack(
        @Query("term") term:String,
        @Query("entity") entity:String,
        @Query("limit") limit: Int,
        @Query("offset") offset:Int
    ) : Response<ITunesResponse>
}