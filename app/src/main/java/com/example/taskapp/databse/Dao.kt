package com.example.taskapp.databse

import android.graphics.ColorSpace.Model
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskapp.model.PostModel


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItems(postModel: List<PostModel>)

    @Query(value = "SELECT * FROM POSTS")
    suspend fun getPosts(): List<PostModel>



}