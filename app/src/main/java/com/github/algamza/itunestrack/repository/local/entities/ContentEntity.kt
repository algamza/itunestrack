package com.github.algamza.itunestrack.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContentEntity(
    @PrimaryKey
    var id : Int,
    var name : String,
    var group: String,
    var artist: String,
    var url : String
)
