package com.example.flo.dataclasses

data class Song(
    val num : String? = "01",
    var title: String = "",
    var singer: String = "",
    var second: Int = 0,
    var playTime: Int = 0,
    var isPlaying: Boolean = false,
    var music: String = ""
    //val img: Int = R.drawable.img_album_exp2
)
