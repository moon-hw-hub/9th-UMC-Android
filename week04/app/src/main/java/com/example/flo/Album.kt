package com.example.flo

data class Album(
    var title: String? = "",
    var singer: String? = "",
    var coverImage: Int? = null,
    // 수록곡 정보용 arraylist
    var songs: ArrayList<Song>? = arrayListOf()
)

//data class TrackSong(
//    var title: String? = "",
//    var singer: String? = ""
//)
