package com.github.algamza.itunestrack.model

data class ContentModel(
    var id : Int,
    var name : String,
    var group: String,
    var artist: String,
    var url : String,
    var favorite: Boolean
)
