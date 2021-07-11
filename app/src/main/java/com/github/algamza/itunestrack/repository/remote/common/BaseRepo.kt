package com.github.algamza.itunestrack.repository.remote.common

import retrofit2.Response

abstract class BaseRepo {
    suspend fun <Any> request(call: suspend () -> Response<Any>): Any {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val message = StringBuilder()
            message.append("ERROR Code : ${response.code()}")
            throw Exception(message.toString())
        }
    }
}