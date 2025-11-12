package com.example.flo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SongDao {
    @Insert
    fun insert(song: Song)

    @Update
    fun update(song: Song)

    @Delete
    fun delete(song: Song)

    //SELECT:데이터를 선택해서 가져와라, *: 모든 데이터를, FROM: 어디서 가져올지
    @Query("SELECT*FROM SongTable")
    fun getSongs(): List<Song>

    //WHERE을 조건문처럼 사용, id값으로 해당하는 송을 받아옴
    @Query("SELECT*FROM SongTable WHERE id=:id")
    fun getSong(id: Int): Song

}