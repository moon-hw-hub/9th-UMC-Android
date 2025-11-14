package com.example.flo.data

import androidx.room.*
@Dao
interface AlbumDao {
    @Insert
    fun insert(album: Album)

    @Update
    fun update(album: Album)

    @Delete
    fun delete(album: Album)

    @Query("SELECT * FROM AlbumTable") // 테이블의 모든 값을 가져와라
    fun getAlbums(): List<Album>

    //WHERE을 조건문처럼 사용, id값으로 해당하는 앨범을 받아옴
    @Query("SELECT*FROM AlbumTable WHERE id=:id")
    fun getAlbum(id: Int): Album

}