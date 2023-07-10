package com.example.taskapp.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskapp.model.PostModel

@Database(entities = [PostModel::class], version = 1,exportSchema = false)
abstract class PostDatabase:RoomDatabase() {
    abstract fun appDao(): Dao
}
private lateinit var INSTANCE : PostDatabase

fun getDatabase(context: Context): PostDatabase {

    synchronized(Database::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PostDatabase::class.java,
                "database"
            ).build()
        }
    }

    return INSTANCE
}