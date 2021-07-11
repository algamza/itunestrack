package com.github.algamza.itunestrack.repository.remote

import com.github.algamza.itunestrack.repository.remote.api.ITunesService
import com.github.algamza.itunestrack.repository.remote.common.BaseRepo
import com.github.algamza.itunestrack.repository.remote.response.ITunesResponse
import org.json.JSONObject
import retrofit2.Response

class RemoteRepo(private val iTunesService: ITunesService) : BaseRepo() {
    suspend fun requestTrack(term:String, entity:String, limit:Int, offset:Int): ITunesResponse =
        request { iTunesService.requestTrack(term,entity,limit,offset) }
}