package com.example.flo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Album(
    var id: Int = 0,

    var title: String? = "",
    var singer: String? = "",
    var isLike: Boolean = false,
    var coverImage: Int? = null,
    // 수록곡 정보용 arraylist
    var songs: ArrayList<Song>? = arrayListOf(),
    //앨범 출시일/정규앨범 유무/장르
    val information: String? = ""
)