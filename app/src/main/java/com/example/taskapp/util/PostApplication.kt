package com.example.taskapp.util

import android.app.Application
import com.example.taskapp.databse.getDatabase
import com.example.taskapp.repo.Repository
import com.example.taskapp.retrofit.RetrofitInterface

class PostApplication : Application() {

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        intialize()
    }

    private fun intialize() {
        val database = getDatabase(applicationContext)
        repository = Repository(RetrofitInterface.getInstance(), database)
    }

}