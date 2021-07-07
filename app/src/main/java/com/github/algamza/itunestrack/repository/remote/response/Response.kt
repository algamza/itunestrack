package com.github.algamza.itunestrack.repository.remote.response

data class Content (
        val trackId: Int,
        val trackName: String,
        val collectionName: String,
        val artistName: String,
        val artworkUrl60: String
)

data class ITunesResponse (
        val resultCount: Int,
        val results: ArrayList<Content>
)

