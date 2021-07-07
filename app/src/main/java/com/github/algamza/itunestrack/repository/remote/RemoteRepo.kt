package com.github.algamza.itunestrack.repository.remote

import com.github.algamza.itunestrack.repository.remote.api.ITunesService
import com.github.algamza.itunestrack.repository.remote.response.ITunesResponse
import org.json.JSONObject
import retrofit2.Response

class RemoteRepo(private val iTunesService: ITunesService) {
    suspend fun requestTrack(term:String, entity:String, limit:Int, offset:Int): ITunesResponse {
        var call : suspend () -> Response<ITunesResponse> =
            { iTunesService.requestTrack(term,entity,limit,offset) }
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val msg = StringBuilder()
            msg.append("ERROR Code : ${response.code()}")
            throw Exception(msg.toString())
        }
    }
}