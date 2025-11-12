package com.example.flo.data

data class Album(
    var title: String? = "",
    var singer: String? = "",
    var coverImage: Int? = null,
    // 수록곡 정보용 arraylist
    var songs: ArrayList<Song>? = arrayListOf(),
    //앨범 출시일/정규앨범 유무/장르
    val information: String? = ""
)