package com.github.algamza.itunestrack.repository.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import javax.annotation.Nullable

data class ContentFavoriteEntity(
        @Embedded var content: ContentEntity,
        @Relation(
                parentColumn = "id",
                entityColumn = "id",
                entity = FavoriteEntity::class
        )
        @Nullable var favorite: FavoriteEntity?
        )
