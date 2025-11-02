package com.example.flo.dataclasses

data class PlayedSong(
    val title : String = "",
    val singer : String = "",
    var second: Int = 0,
    var playTime: Int = 0,
    var isPlaying: Boolean = false
)
