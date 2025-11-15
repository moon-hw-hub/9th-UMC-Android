package com.example.flo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    //모든 유저들의 정보를 가져옴
    @Query("SELECT*FROM UserTable")
    fun getUsers(): List<User>

    //유저 한 명의 정보를 가져옴
    @Query("SELECT*FROM UserTable WHERE email= :email AND password = :password")
    fun getUser(email:String, password:String) : User? //정보가 있을수도 있고 없을수도 있으니 nullable처리
}