package com.github.algamza.itunestrack.repository.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import javax.annotation.Nullable

data class FavoriteContentEntity (
        @Embedded
        var favorite: FavoriteEntity,
        @Relation(
                parentColumn = "id",
                entityColumn = "id",
                entity = ContentEntity::class
        )
        @Nullable var content: ContentEntity?
    )