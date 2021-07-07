package com.github.algamza.itunestrack.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    @PrimaryKey
    var id : Int,
    var favorite: Boolean
)