package com.github.algamza.itunestrack.view.list

data class ListData(
        var callback: Callback,
        var id : Int,
        var name : String,
        var group: String,
        var artist: String,
        var url : String,
        var favorite: Boolean
) {
    interface Callback {
        fun onClickFavorite(id: Int, favorite: Boolean)
    }
}
